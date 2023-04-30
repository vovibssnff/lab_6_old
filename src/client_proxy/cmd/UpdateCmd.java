package client_proxy.cmd;

import client_proxy.io.Mode;
import client_proxy.io.OutputEngine;
import client_proxy.receivers.CollectionReceiver;

import java.util.Scanner;

public class UpdateCmd implements Command {
    private CollectionReceiver collection_receiver;
    private int id;
    public UpdateCmd(CollectionReceiver collection_receiver) {
        this.collection_receiver=collection_receiver;
    }
    @Override
    public void execute(String arg, Scanner scanner, Mode mode) {
        if (arg!=null) {
            try {
                collection_receiver.update(arg, scanner, mode);
            } catch (RuntimeException e) {
                System.out.println(OutputEngine.incorrectLongArg());
            }
        }
    }
    public static String getName() {return "update";}
}
