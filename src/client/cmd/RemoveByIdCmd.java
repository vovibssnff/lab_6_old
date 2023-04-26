package client.cmd;

import client.io.Validator;
import client.managment.UsrInputReceiver;
import server.io.OutputEngine;

public class RemoveByIdCmd implements Command {
    private UsrInputReceiver receiver;
    private Long id;
    public RemoveByIdCmd(UsrInputReceiver receiver) {
        this.receiver=receiver;
    }
    @Override
    public boolean validate(String arg) {
        return UsrInputReceiver.longValidator(arg);
    }
    public static String getName() {return "remove_by_id";}
}
