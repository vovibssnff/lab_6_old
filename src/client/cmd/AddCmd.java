package client.cmd;

import client.managment.UsrInputReceiver;

public class AddCmd implements Command {
    private UsrInputReceiver collection_receiver;
    public AddCmd(UsrInputReceiver collection_receiver) {this.collection_receiver=collection_receiver;}

    @Override
    public boolean validate(String arg, Mode mode) {
        return UsrInputReceiver.setElemScript();
    }
    public static String getName() {
        return "add";
    }
}
