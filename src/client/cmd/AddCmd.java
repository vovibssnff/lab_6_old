package client.cmd;

import client.io.Mode;
import client.managment.UsrInputReceiver;

public class AddCmd implements Command {
    private UsrInputReceiver collection_receiver;
    public AddCmd(UsrInputReceiver collection_receiver) {this.collection_receiver=collection_receiver;}

    @Override
    public void execute(String arg) {
        UsrInputReceiver.setElemScript(null);
    }
    public static String getName() {
        return "add";
    }
}
