package common.cmd;

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
    public void setArg(String arg) {
        LabWork elem = this.usrInputReceiver.add();
        if (elem!=null) {
            this.elem=elem;
        }
    }
    @Override
    public void execute() {
        if (this.elem!=null) {
            this.labWorkService.addElem(this.elem);
        }
    }
    public static String getName() {
        return "add";
    }
}
