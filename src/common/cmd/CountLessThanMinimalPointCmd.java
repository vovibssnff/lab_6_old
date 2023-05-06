package common.cmd;

import client.managment.LabWorkService;
import client.managment.UsrInputReceiver;
import common.OutputEngine;

public class CountLessThanMinimalPointCmd implements Command {
    private UsrInputReceiver usrInputReceiver;
    private LabWorkService labWorkService;
    private Double minimalPoint;
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
        if (this.usrInputReceiver.typeValidator(arg, Double.class)) {
            this.minimalPoint = this.usrInputReceiver.setArg(arg, Double.class);
        }
    }
    @Override
    public void execute() {
        if (this.minimalPoint!=null) {
            this.labWorkService.countLessThanMinimalPoint(this.minimalPoint);
        } else {
            System.out.println(OutputEngine.incorrectDoubleArg());
        }
    }
    public static String getName() {return "cltmp";}
}
