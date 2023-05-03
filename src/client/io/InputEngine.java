package client.io;

import client.load.CollectionLoader;
import client.load.Parser;
import client.managment.*;
import common.cmd.*;
import client.managment.ProgramState;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Лончер, запускающий основной сканер, который считывает команды и аргументы в двух режимах: из файла и в формате обычного ввода через консоль
 */
public class InputEngine {
    private static final UsrInputReceiver receiver = new UsrInputReceiver();
    private static final LabWorkService proxy = new LabWorkService();
    private static final File tmpFile = new File("unsaved.tmp");
    public static String resp = null;
    private static void launchInvoke(Command command, String arg) {
        command.setReceivers(ProgramState.getUsrInputReceiver(), ProgramState.getLabWorkService());
        if (command.setArg(arg)) {
            command.execute();
        }
    }
    public static void init() {
        CollectionsEngine.addElemToCommandMap(AddCmd.getName(), new AddCmd());
        CollectionsEngine.addElemToCommandMap(HelpCmd.getName(), new HelpCmd());
        CollectionsEngine.addElemToCommandMap(SoutCollectionCmd.getName(), new SoutCollectionCmd());
        CollectionsEngine.addElemToCommandMap(HistoryCmd.getName(), new HistoryCmd());
        CollectionsEngine.addElemToCommandMap(PrintUniqueAuthorCmd.getName(), new PrintUniqueAuthorCmd());
        CollectionsEngine.addElemToCommandMap(ClearCmd.getName(), new ClearCmd());
        CollectionsEngine.addElemToCommandMap(SaveCmd.getName(), new SaveCmd());
        CollectionsEngine.addElemToCommandMap(HeadCmd.getName(), new HeadCmd());
        CollectionsEngine.addElemToCommandMap(InfoCmd.getName(), new InfoCmd());
        CollectionsEngine.addElemToCommandMap(ExitCmd.getName(), new ExitCmd());
        CollectionsEngine.addElemToCommandMap(UpdateCmd.getName(), new UpdateCmd());
        CollectionsEngine.addElemToCommandMap(PrintFieldDescendingMinimalPointCmd.getName(), new PrintFieldDescendingMinimalPointCmd());
        CollectionsEngine.addElemToCommandMap(CountLessThanMinimalPointCmd.getName(), new CountLessThanMinimalPointCmd());
        CollectionsEngine.addElemToCommandMap(RemoveLowerCmd.getName(), new RemoveLowerCmd());
        CollectionsEngine.addElemToCommandMap(RemoveByIdCmd.getName(), new RemoveByIdCmd());
        CollectionsEngine.addElemToCommandMap(ExecuteScriptCmd.getName(), new ExecuteScriptCmd());
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
        ProgramState.setUsrInputReceiver(new UsrInputReceiver());
        ProgramState.setLabWorkService(new LabWorkService());
        modeSwitcher(null, null);
    }
    public static void scanCommand(String[] tokens, Command currentCommand, File tmpFile) {
        String input = ProgramState.getScanner().nextLine().trim();
        tokens = input.split(" ");
        Command command = CollectionsEngine.searchCommand(tokens[0]);

        try {
            currentCommand = command.getClass().getConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        if (tokens.length<2) {
            launchInvoke(currentCommand, null);
        } else {
            launchInvoke(currentCommand, tokens[1]);
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
                    ProgramState.setMode(client.io.Mode.DEFAULT);
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
