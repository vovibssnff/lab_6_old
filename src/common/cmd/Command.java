package common.cmd;


import client.managment.LabWorkService;
import client.managment.UsrInputReceiver;

public interface Command {

    default void execute() {}
    default boolean setArg(String arg) {return true;}
    void setReceivers(UsrInputReceiver usrInputReceiver, LabWorkService receiver);
}
