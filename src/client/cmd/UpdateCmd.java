package client.cmd;

import client.managment.UsrInputReceiver;

public class UpdateCmd implements Command {
    private UsrInputReceiver receiver;
    private int id;
    public UpdateCmd(UsrInputReceiver receiver) {
        this.receiver=receiver;
    }
    @Override
    public void execute(String arg) {
        UsrInputReceiver.setElem(arg);
    }
    public static String getName() {return "update";}
}
