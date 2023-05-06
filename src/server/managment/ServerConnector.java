package server.managment;

import com.google.gson.Gson;
import server.ConnectionService;
import common.OutputEngine;
import common.cmd.*;
import server.load.CollectionLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Лончер, запускающий основной сканер, который считывает команды и аргументы в двух режимах: из файла и в формате обычного ввода через консоль
 */
public class ServerConnector {
    private static final CollectionReceiver receiver = new CollectionReceiver();
    private static final File tmpFile = new File("unsaved.tmp");
    public static String resp = null;
    public static void init() {
//        Collections.addElemsFromList(Parser.parse());
//        Collections.sortCollection();
        ServerState.setGson(new Gson());
        System.out.println(OutputEngine.greeting_msg());
        Scanner keyboardScanner = new Scanner(System.in);

        ConnectionService.connect();
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
