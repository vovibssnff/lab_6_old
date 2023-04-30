package client_proxy.cmd;
import client_proxy.io.Mode;
import client_proxy.receivers.CollectionReceiver;

import java.util.Scanner;

public class HelpCmd implements Command {
    private CollectionReceiver collection_receiver;
    public HelpCmd(CollectionReceiver collection_receiver) {
        this.collection_receiver=collection_receiver;
    }
    public void execute(String arg, Scanner scanner, Mode mode) {
        collection_receiver.help();
    }
    public static String getName() {return "help";}
}
