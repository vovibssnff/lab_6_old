package server.io;
import server.managment.Collections;
import server.managment.CollectionReceiver;
import common.cmd.*;
import server.load.CollectionLoader;
import server.load.Parser;
import server.managment.ServerState;

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
        Collections.addElemToCommandMap(AddCmd.getName(), new AddCmd(receiver));
        Collections.addElemToCommandMap(HelpCmd.getName(), new HelpCmd(receiver));
        Collections.addElemToCommandMap(SoutCollectionCmd.getName(), new SoutCollectionCmd(receiver));
        Collections.addElemToCommandMap(HistoryCmd.getName(), new HistoryCmd(receiver));
        Collections.addElemToCommandMap(PrintUniqueAuthorCmd.getName(), new PrintUniqueAuthorCmd(receiver));
        Collections.addElemToCommandMap(ClearCmd.getName(), new ClearCmd(receiver));
        Collections.addElemToCommandMap(SaveCmd.getName(), new SaveCmd(receiver));
        Collections.addElemToCommandMap(HeadCmd.getName(), new HeadCmd(receiver));
        Collections.addElemToCommandMap(InfoCmd.getName(), new InfoCmd(receiver));
        Collections.addElemToCommandMap(ExitCmd.getName(), new ExitCmd(receiver));
        Collections.addElemToCommandMap(UpdateCmd.getName(), new UpdateCmd(receiver));
        Collections.addElemToCommandMap(PrintFieldDescendingMinimalPointCmd.getName(), new PrintFieldDescendingMinimalPointCmd(receiver));
        Collections.addElemToCommandMap(CountLessThanMinimalPointCmd.getName(), new CountLessThanMinimalPointCmd(receiver));
        Collections.addElemToCommandMap(RemoveLowerCmd.getName(), new RemoveLowerCmd(receiver));
        Collections.addElemToCommandMap(RemoveByIdCmd.getName(), new RemoveByIdCmd(receiver));
        Collections.addElemToCommandMap(ExecuteScriptCmd.getName(), new ExecuteScriptCmd(receiver));
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
