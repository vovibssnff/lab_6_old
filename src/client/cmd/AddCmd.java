package client.cmd;

import client.data.LabWork;
import client.managment.ProxyReceiver;
import client.managment.UsrInputReceiver;

public class AddCmd implements Command {
    private UsrInputReceiver receiver;
    private ProxyReceiver proxy;
    private LabWork elem;
    public AddCmd(UsrInputReceiver receiver, ProxyReceiver proxy) {
        this.receiver=receiver;
        this.proxy=proxy;
    }

    @Override
    public boolean validate(String arg) {
        this.elem=UsrInputReceiver.setElemScript(null);
        if (!this.elem.equals(null)) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public void execute() {proxy.addElem();}
    public static String getName() {
        return "add";
    }
}
