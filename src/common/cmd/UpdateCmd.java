package common.cmd;

import client.managment.UsrInputReceiver;

public class UpdateCmd implements Command {
    private UsrInputReceiver receiver;
    private int id;
    public UpdateCmd(UsrInputReceiver receiver) {
        this.receiver=receiver;
    }
    @Override
    public boolean validate(String arg) {
        return UsrInputReceiver.addValidator(arg);
    }
    public static String getName() {return "update";}
}
