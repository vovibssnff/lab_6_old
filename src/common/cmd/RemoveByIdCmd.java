package common.cmd;

import client.managment.LabWorkService;
import client.managment.UsrInputReceiver;

public class RemoveByIdCmd implements Command {
    private UsrInputReceiver usrInputReceiver;
    private LabWorkService labWorkService;
    private Long id;

    @Override
    public void setUsrInputReceiver(UsrInputReceiver usrInputReceiver) {
        this.usrInputReceiver=usrInputReceiver;
    }

    @Override
    public void setLabWorkService(LabWorkService labWorkService) {
        this.labWorkService = labWorkService;
    }
    @Override
    public void setArg(String arg) {
        if (this.usrInputReceiver.typeValidator(arg, Long.class)) {
            this.id = this.usrInputReceiver.setArg(arg, Long.class);
        }
    }
    @Override
    public void execute() {
        this.labWorkService.removeById(this.id);
    }
    public static String getName() {return "remove_by_id";}
}
