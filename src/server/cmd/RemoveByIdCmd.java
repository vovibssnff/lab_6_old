package server.cmd;

import server.io.Mode;
import server.io.OutputEngine;
import server.managment.CollectionReceiver;

import java.util.Scanner;

public class RemoveByIdCmd implements Command{
    private CollectionReceiver collection_receiver;
    private double id;
    public RemoveByIdCmd(CollectionReceiver collection_receiver) {
        this.collection_receiver=collection_receiver;
    }
    @Override
    public void execute(String arg, Scanner scanner, Mode mode) {
        if (arg!=null) {
            try {
                collection_receiver.remove_by_id(Long.parseLong(arg));
            } catch (RuntimeException e) {
                System.out.println(OutputEngine.incorrectLongArg());
            }
        }
    }
    public static String getName() {return "remove_by_id";}
}
