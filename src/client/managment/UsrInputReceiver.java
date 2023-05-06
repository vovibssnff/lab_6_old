package client.managment;

import client.io.*;
import common.OutputEngine;
import common.data.LabWork;

import java.lang.reflect.InvocationTargetException;

/**
 * Класс, выполняющий команды
 */
public class UsrInputReceiver {
    public int iterations = 0;

    public void exit() {
        System.exit(0);
    }

    public LabWork add() {
        return ElemInputService.setElemScript(null);
    }
    public LabWork update(Long arg) {return ElemInputService.setElemScript(arg);}
    public <T> boolean typeValidator(String arg, Class<T> clazz) {
        if (arg!=null && !arg.equals("")) {
            try {
                clazz.getConstructor(String.class).newInstance(arg);
                return true;
            } catch (Exception e) {
                System.out.println(OutputEngine.incorrectArg()+clazz.getName());
            }
        }
        return false;
    }

    public <T> T setArg(String arg, Class<T> clazz) {
        try {
            return clazz.getConstructor(String.class).newInstance(arg);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
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




}
