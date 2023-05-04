package common.cmd;


import client.managment.LabWorkService;
import client.managment.UsrInputReceiver;
import common.data.LabWork;

public interface Command {

    default void execute() {}
    default boolean setArg(String arg) {return true;}
    default void setUsrInputReceiver(UsrInputReceiver usrInputReceiver) {}
    default void setLabWorkService(LabWorkService labWorkService) {}
}
