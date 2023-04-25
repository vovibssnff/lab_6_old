package server.cmd;

import server.io.Mode;
import server.managment.CollectionReceiver;

import java.util.Scanner;

public class InfoCmd implements Command{
    private CollectionReceiver collection_receiver;
    public InfoCmd(CollectionReceiver collection_receiver) {this.collection_receiver=collection_receiver;}
    @Override
    public void execute(String arg, Scanner scanner, Mode mode) {collection_receiver.info();}
    public static String getName() {return "info";}
}
