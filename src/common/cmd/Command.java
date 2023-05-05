package common.cmd;


import client.managment.LabWorkService;
import client.managment.UsrInputReceiver;
import common.data.LabWork;

public interface Command {

    void execute();
    default void setArg(String arg) {}
    default void setUsrInputReceiver(UsrInputReceiver usrInputReceiver) {}
    default void setLabWorkService(LabWorkService labWorkService) {}
}
