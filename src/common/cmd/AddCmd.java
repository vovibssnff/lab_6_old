package common.cmd;

import client.io.ElemInputService;
import client.managment.LabWorkService;
import common.data.LabWork;
import client.managment.UsrInputReceiver;

public class AddCmd implements Command {
    private UsrInputReceiver usrInputReceiver;
    private LabWorkService labWorkService;
    private LabWork elem;
    public void setReceivers(UsrInputReceiver usrInputReceiver, LabWorkService labWorkService) {
        this.usrInputReceiver=usrInputReceiver;
        this.labWorkService = labWorkService;
    }
    @Override
    public boolean setArg(String arg) {
        this.elem= ElemInputService.setElemScript(null);
        if (!this.elem.equals(null)) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public void execute() {this.labWorkService.addElem(this.getArg());}
    public LabWork getArg() {return this.elem;}
    public static String getName() {
        return "add";
    }
}
