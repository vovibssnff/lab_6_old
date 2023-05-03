package common.cmd;

import client.io.Validator;
import client.managment.UsrInputReceiver;
import client.io.OutputEngine;

public class CountLessThanMinimalPointCmd implements Command {
    private UsrInputReceiver receiver;
    private double minimalPoint;

    @Override
    public boolean setArg(String arg) {
        if (arg!=null) {
            try {
                return Validator.checkMinimalPoint(Double.parseDouble(arg));
            } catch (RuntimeException e) {
                System.out.println(OutputEngine.incorrectDoubleArg());
            }
        }
        return false;
    }
    public static String getName() {return "cltmp";}
}
