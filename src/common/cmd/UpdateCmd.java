package common.cmd;

import client.managment.LabWorkService;
import client.managment.UsrInputReceiver;
import common.data.LabWork;

public class UpdateCmd implements Command {
    private UsrInputReceiver usrInputReceiver;
    private LabWorkService labWorkService;
    private Long id;
    private LabWork labWork;
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
        if (this.usrInputReceiver.typeValidator(arg, Long.class)) {
            this.id = this.usrInputReceiver.setArg(arg, Long.class);
            this.labWork = this.usrInputReceiver.update(this.id);
        }
    }
    @Override
    public void execute() {
        this.labWorkService.update(this.labWork);
    }
    public static String getName() {return "update";}
}
