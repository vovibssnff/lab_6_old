package client.io;

import client.connect.Transmitter;
import client.load.CollectionLoader;
import client.load.Parser;
import client.managment.*;
import client.cmd.*;
import client.managment.PrgrmState;

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
    private static final ProxyReceiver proxy = new ProxyReceiver();
    private static final File tmpFile = new File("unsaved.tmp");
    public static String resp = null;
    private static void validate(Command command, String arg) {
        command.validate(arg);
        Transmitter transmitter = new Transmitter();
        //TODO генерация инстанса Transmitter для передачи на сервак
    }
    public static void init() {
        CollectionsEngine.addElemToCommandMap(AddCmd.getName(), new AddCmd(receiver, proxy));
        CollectionsEngine.addElemToCommandMap(HelpCmd.getName(), new HelpCmd());
        CollectionsEngine.addElemToCommandMap(SoutCollectionCmd.getName(), new SoutCollectionCmd());
        CollectionsEngine.addElemToCommandMap(HistoryCmd.getName(), new HistoryCmd());
        CollectionsEngine.addElemToCommandMap(PrintUniqueAuthorCmd.getName(), new PrintUniqueAuthorCmd());
        CollectionsEngine.addElemToCommandMap(ClearCmd.getName(), new ClearCmd());
        CollectionsEngine.addElemToCommandMap(SaveCmd.getName(), new SaveCmd(receiver));
        CollectionsEngine.addElemToCommandMap(HeadCmd.getName(), new HeadCmd());
        CollectionsEngine.addElemToCommandMap(InfoCmd.getName(), new InfoCmd());
        CollectionsEngine.addElemToCommandMap(ExitCmd.getName(), new ExitCmd(receiver));
        CollectionsEngine.addElemToCommandMap(UpdateCmd.getName(), new UpdateCmd(receiver));
        CollectionsEngine.addElemToCommandMap(PrintFieldDescendingMinimalPointCmd.getName(), new PrintFieldDescendingMinimalPointCmd());
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
        ProgramState.setScanner(keyboardScanner);
        modeSwitcher(null, null);
    }
    public static void scanCommand(String[] tokens, Command currentCommand, File tmpFile) {
        String input = ProgramState.getScanner().nextLine().trim();
        tokens = input.split(" ");
        currentCommand = CollectionsEngine.searchCommand(tokens[0]);
        if (tokens.length<2) {
            validate(currentCommand, null);
        } else {
            validate(currentCommand, tokens[1]);
        }
        CollectionLoader.save(tmpFile);
    }
    public static void modeSwitcher(Command currentCommand, String filename) {
        String[] tokens = new String[0];
        File file = null;
        try {
            file = new File(filename);
        } catch (NullPointerException e) {
            System.out.print(OutputEngine.prompt());
        }
        switch (ProgramState.getMode()) {

            //Режим чтения команд с клавиатуры
            case DEFAULT -> {
                //Основной сканер
                while (true) {
                    try {
                        ProgramState.setMode(Mode.DEFAULT);
                        System.out.print(OutputEngine.prompt());
                        scanCommand(tokens, currentCommand, tmpFile);
                    } catch (NullPointerException e) {
                        System.out.println(OutputEngine.incorrectCommand());
                    }
                }
            }

            //Режим чтения команд из скрипта
            case FILE -> {

                Scanner fileScanner = null;
                try {
                    assert file != null;
                    fileScanner = new Scanner(file);
                    ProgramState.setScanner(fileScanner);
                    PrgrmState.setMode(client.io.Mode.DEFAULT);
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
                    scanCommand(tokens, currentCommand, tmpFile);
                }
            }
        }
    }
}
