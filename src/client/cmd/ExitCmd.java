package client.cmd;

import client.managment.UsrInputReceiver;

public class ExitCmd implements Command {
    private UsrInputReceiver collection_receiver;
    public ExitCmd(UsrInputReceiver collection_receiver) {this.collection_receiver=collection_receiver;}
    public static String getName() {return "exit";}
}
