package common.cmd;

import client.managment.UsrInputReceiver;

public class ExitCmd implements Command {
    private UsrInputReceiver collection_receiver;
    public static String getName() {return "exit";}
}
