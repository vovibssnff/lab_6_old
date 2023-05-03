package common.cmd;

import client.managment.UsrInputReceiver;

public class RemoveByIdCmd implements Command {
    private UsrInputReceiver receiver;
    private Long id;
    @Override
    public boolean setArg(String arg) {
        return UsrInputReceiver.longValidator(arg);
    }
    public static String getName() {return "remove_by_id";}
}
