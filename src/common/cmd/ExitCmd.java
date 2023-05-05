package common.cmd;

import client.io.OutputEngine;
import client.managment.UsrInputReceiver;

public class ExitCmd implements Command {

    @Override
    public void execute() {
        System.exit(0);
    }
    public static String getName() {return "exit";}
}
