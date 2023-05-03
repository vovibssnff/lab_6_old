package client.managment;

import client.io.*;
import common.data.LabWork;

/**
 * Класс, выполняющий команды
 */
public class UsrInputReceiver {
    public int iterations = 0;

    public void update(String arg) {
        CollectionsEngine.update(CollectionsEngine.searchInCollection(Long.parseLong(arg)), ElemInputService.setElemScript(Long.parseLong(arg)));
    }

    public void exit() {
        System.exit(0);
    }

    public LabWork add(String arg) {
        return ElemInputService.setElemScript(null);
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
