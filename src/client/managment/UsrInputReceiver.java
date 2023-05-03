package client.managment;

import client.io.*;
import common.data.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Класс, выполняющий команды
 */
public class UsrInputReceiver {
    private static Scanner sc;
    public int iterations = 0;

    public static void setScanner(Scanner scanner) {
        sc=scanner;
    }

    public void update(String arg) {
        CollectionsEngine.update(CollectionsEngine.searchInCollection(Long.parseLong(arg)), ElemInputService.setElemScript(Long.parseLong(arg)));
    }

    public void exit() {
        System.exit(0);
    }
    public static boolean addValidator(String arg) {
        ElemInputService.setElemScript(null);
        return true;
    }
    public static boolean longValidator(String arg) {
        if (arg!=null) {
            try {
                switch (ProgramState.getMode()) {
                    case ADD -> {
                        ElemInputService.setElemScript(null);
                        return true;
                    }
                    case UPDATE -> {
                        ElemInputService.setElemScript(Long.parseLong(arg));
                        return true;
                    }
                }
            } catch (RuntimeException e) {
                System.out.println(server.io.OutputEngine.incorrectLongArg());
                return false;
            }
        }
        return false;
    }
    public void executeScriptValidation(String filename) {
        ProgramState.setMode(Mode.FILE);
        iterations++;
        if (iterations>499) {
            System.out.println(OutputEngine.stackOverflowError());
            return;
        }
        InputEngine.modeSwitcher(null, filename);
    }
    public void removeLowerValidation(long id) {
        CollectionsEngine.removeLower(id);
    }
    public static boolean countLessThanMinimalPointValidation(double minimal_point) {
        return Validator.checkMinimalPoint(minimal_point);
    }




}
