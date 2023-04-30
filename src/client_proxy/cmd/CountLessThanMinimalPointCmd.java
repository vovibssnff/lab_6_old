package client_proxy.cmd;

import client_proxy.io.Mode;
import client_proxy.io.OutputEngine;
import client_proxy.receivers.CollectionReceiver;

import java.util.Scanner;

public class CountLessThanMinimalPointCmd implements Command {
    private CollectionReceiver collection_receiver;
    private double minimalPoint;
    public CountLessThanMinimalPointCmd(CollectionReceiver collection_receiver) {
        this.collection_receiver=collection_receiver;
    }
    @Override
    public void execute(String arg)  {
        if (arg!=null) {
            try {
                collection_receiver.count_less_than_minimal_point(Double.parseDouble(arg));
            } catch (RuntimeException e) {
                System.out.println(OutputEngine.incorrectDoubleArg());
            }
        }
    }
    public static String getName() {return "cltmp";}
}
