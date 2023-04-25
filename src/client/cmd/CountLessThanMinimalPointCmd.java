package client.cmd;

import client.managment.UsrInputReceiver;
import client.io.OutputEngine;

public class CountLessThanMinimalPointCmd implements Command {
    private UsrInputReceiver receiver;
    private double minimalPoint;
    public CountLessThanMinimalPointCmd(UsrInputReceiver collection_receiver) {
        this.receiver=collection_receiver;
    }

    @Override
    public boolean validate(String arg) {
        if (arg!=null) {
            try {
                receiver.countLessThanMinimalPoint(Double.parseDouble(arg));
            } catch (RuntimeException e) {
                System.out.println(OutputEngine.incorrectDoubleArg());
            }
        }
        return false;
    }
    public static String getName() {return "cltmp";}
}
