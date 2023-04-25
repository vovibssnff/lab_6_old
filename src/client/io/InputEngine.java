package client.io;

import client.load.CollectionLoader;
import client.load.Parser;
import client.managment.UsrInputReceiver;
import client.managment.CollectionsEngine;
import client.cmd.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Лончер, запускающий основной сканер, который считывает команды и аргументы в двух режимах: из файла и в формате обычного ввода через консоль
 */
public class InputEngine {
    private static final UsrInputReceiver receiver = new UsrInputReceiver();
    private static final File tmpFile = new File("unsaved.tmp");
    public static String resp = null;
    public static void executeCommand(Command command, String arg, Scanner scanner, Mode mode) {
        command.validate(arg);
        //TODO генерация инстанса Transmitter для передачи на сервак
    }
    public static void init() {
        CollectionsEngine.addElemToCommandMap(AddCmd.getName(), new AddCmd(receiver));
        CollectionsEngine.addElemToCommandMap(HelpCmd.getName(), new HelpCmd());
        CollectionsEngine.addElemToCommandMap(SoutCollectionCmd.getName(), new SoutCollectionCmd(receiver));
        CollectionsEngine.addElemToCommandMap(HistoryCmd.getName(), new HistoryCmd(receiver));
        CollectionsEngine.addElemToCommandMap(PrintUniqueAuthorCmd.getName(), new PrintUniqueAuthorCmd(receiver));
        CollectionsEngine.addElemToCommandMap(ClearCmd.getName(), new ClearCmd(receiver));
        CollectionsEngine.addElemToCommandMap(SaveCmd.getName(), new SaveCmd(receiver));
        CollectionsEngine.addElemToCommandMap(HeadCmd.getName(), new HeadCmd());
        CollectionsEngine.addElemToCommandMap(InfoCmd.getName(), new InfoCmd(null));
        CollectionsEngine.addElemToCommandMap(ExitCmd.getName(), new ExitCmd(receiver));
        CollectionsEngine.addElemToCommandMap(UpdateCmd.getName(), new UpdateCmd(receiver));
        CollectionsEngine.addElemToCommandMap(PrintFieldDescendingMinimalPointCmd.getName(), new PrintFieldDescendingMinimalPointCmd(receiver));
        CollectionsEngine.addElemToCommandMap(CountLessThanMinimalPointCmd.getName(), new CountLessThanMinimalPointCmd(receiver));
        CollectionsEngine.addElemToCommandMap(RemoveLowerCmd.getName(), new RemoveLowerCmd(receiver));
        CollectionsEngine.addElemToCommandMap(RemoveByIdCmd.getName(), new RemoveByIdCmd(receiver));
        CollectionsEngine.addElemToCommandMap(ExecuteScriptCmd.getName(), new ExecuteScriptCmd(receiver));
        CollectionsEngine.addElemsFromList(Parser.parse());
        CollectionsEngine.sortCollection();
        System.out.println(OutputEngine.greeting_msg());
        Scanner keyboardScanner = new Scanner(System.in);

        Pattern pattern = Pattern.compile("^[yn]$");
        //Восстановление старых данных
        if (tmpFile.exists()&&tmpFile.length()!=0) {
            do {
                try {

                    System.out.println(OutputEngine.collectionRestore());
                    System.out.print(OutputEngine.prompt());
                    resp = keyboardScanner.nextLine().trim();
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                }
            } while (!pattern.matcher(resp).matches());
            if (resp.equals("y")) {
                CollectionLoader.load(tmpFile);
            }
        }
        launcher(keyboardScanner, Mode.DEFAULT, null, null);
    }
    public static void commandExecute(Scanner scanner, String[] tokens, Command currentCommand, File tmpFile, Mode mode) {
        String input = scanner.nextLine().trim();
        tokens = input.split(" ");
//        System.out.println("input: "+input+"tokens: "+ tokens[0]);
//        if (input.equals("")) {
//            scanner.nextLine();
//            input = scanner.nextLine().trim();
//        }
        //tokens = (String[]) scanner.useDelimiter(" ").tokens().toArray();
        currentCommand = CollectionsEngine.searchCommand(tokens[0]);
        if (tokens.length<2) {
            executeCommand(currentCommand, null, scanner, mode);
        } else {
            executeCommand(currentCommand, tokens[1], scanner, mode);
        }
        CollectionLoader.save(tmpFile);
    }
    public static void launcher(Scanner sc, Mode mode, Command currentCommand, String filename) {
        String[] tokens = new String[0];
        File file = null;
        try {
            file = new File(filename);
        } catch (NullPointerException e) {
            System.out.print(OutputEngine.prompt());
        }
        switch (mode) {

            //Режим чтения команд с клавиатуры
            case DEFAULT -> {
                UsrInputReceiver.setScanner(sc);

                //Основной сканер
                while (true) {
                    try {
                        System.out.print(OutputEngine.prompt());
                        commandExecute(tokens, currentCommand, tmpFile, mode);
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
                    UsrInputReceiver.setScanner(fileScanner);
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
                    commandExecute(fileScanner, tokens, currentCommand, tmpFile, mode);
                }
            }
        }
    }
}
