package client.cmd;

import client.managment.UsrInputReceiver;

public class UpdateCmd implements Command {
    private UsrInputReceiver receiver;
    private int id;
    public UpdateCmd(UsrInputReceiver receiver) {
        this.receiver=receiver;
    }
    public static String getName() {return "update";}
}
