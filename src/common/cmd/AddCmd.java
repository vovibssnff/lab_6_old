package common.cmd;

import client.io.ElemInputService;
import client.managment.LabWorkService;
import common.data.LabWork;
import client.managment.UsrInputReceiver;

public class AddCmd implements Command {
    private UsrInputReceiver usrInputReceiver;
    private LabWorkService labWorkService;
    private LabWork elem;
    @Override
    public void setUsrInputReceiver(UsrInputReceiver usrInputReceiver) {
        this.usrInputReceiver=usrInputReceiver;
    }
    @Override
    public void setLabWorkService(LabWorkService labWorkService) {
        this.labWorkService=labWorkService;
    }
    @Override
    public boolean setArg(String arg) {
        this.elem=usrInputReceiver.add(null);
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
