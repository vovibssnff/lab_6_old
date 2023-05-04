package server.io;
import com.google.gson.Gson;
import server.managment.Collections;
import server.managment.CollectionReceiver;
import common.cmd.*;
import server.load.CollectionLoader;
import server.load.Parser;
import server.managment.ServerState;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.*;

/**
 * Лончер, запускающий основной сканер, который считывает команды и аргументы в двух режимах: из файла и в формате обычного ввода через консоль
 */
public class ServerConnector {
    private static final CollectionReceiver receiver = new CollectionReceiver();
    private static final File tmpFile = new File("unsaved.tmp");
    public static String resp = null;
    public static void init() {
        DatagramChannel channel = DatagramChannel.open();
        try {
            channel.bind(new InetSocketAddress(8080));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            buffer.clear();
            try {
                channel.receive(buffer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            buffer.flip();
            String jsonRequest = new String(buffer.array(), 0, buffer.limit());
            Gson gson = new Gson();
            String request = gson.fromJson(jsonRequest, String.class);
            String responce = "abobus_sus_amogus";
            String jsonResponce = gson.toJson(responce);
            buffer.clear();
            buffer.put(jsonResponce.getBytes());
            buffer.flip();

            try {
                channel.send(buffer, new InetSocketAddress("localhost", 8081));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



        Collections.addElemsFromList(Parser.parse());
        Collections.sortCollection();
        System.out.println(OutputEngine.greeting_msg());
        Scanner keyboardScanner = new Scanner(System.in);

        launcher(null, null);
    }
    public static void commandExecute(Command currentCommand, File tmpFile) {
        currentCommand.execute();
        CollectionLoader.save(tmpFile);
    }
    public static void launcher(Command currentCommand, String filename) {
        String[] tokens = new String[0];
        File file = null;
        try {
            file = new File(filename);
        } catch (NullPointerException e) {
            System.out.print(OutputEngine.prompt());
        }
        switch (ServerState.getMode()) {

            //Режим чтения команд с клавиатуры
            case DEFAULT -> {

                //Основной сканер
                while (true) {
                    try {
                        System.out.print(OutputEngine.prompt());
                        commandExecute(currentCommand, tmpFile);
                    } catch (NullPointerException e) {
                        System.out.println(OutputEngine.incorrectCommand());
                    }
                }
            }

            //Режим чтения команд из скрипта
            case FILE -> {

                Scanner fileScanner = null;
                try {
                    fileScanner = new Scanner(file);
                } catch (FileNotFoundException e) {
                    e.getStackTrace();
                }
                if (!file.exists() || !file.isFile() || !file.canRead()) {
                    System.out.println(OutputEngine.accessError());
                    return;
                }
                while (true) {
                    assert fileScanner != null;
                    if (!fileScanner.hasNextLine()) break;
                    commandExecute(currentCommand, tmpFile);
                }
            }
        }
    }
}
