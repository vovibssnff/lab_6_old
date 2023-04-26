package server.cmd;

import server.io.Mode;
import server.managment.CollectionReceiver;

import java.util.Scanner;

public class ExecuteScriptCmd implements Command{
    private CollectionReceiver collection_receiver;
    private String scriptname;
    public ExecuteScriptCmd(CollectionReceiver collection_receiver) {
        this.collection_receiver=collection_receiver;
    }
    @Override
    public void execute(String arg) {collection_receiver.execute_script(arg);}
    public static String getName() {return "execute_script";}
}
