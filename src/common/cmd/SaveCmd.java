package common.cmd;

import client.managment.UsrInputReceiver;

public class SaveCmd implements Command {
    private UsrInputReceiver collection_receiver;
    public static String getName() {return "save";}
}
