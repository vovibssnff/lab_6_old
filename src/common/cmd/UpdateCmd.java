package common.cmd;

import client.managment.UsrInputReceiver;

public class UpdateCmd implements Command {
    private UsrInputReceiver receiver;
    private int id;
    @Override
    public boolean setArg(String arg) {
        return UsrInputReceiver.update();
    }
    public static String getName() {return "update";}
}
