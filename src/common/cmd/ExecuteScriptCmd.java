package common.cmd;

import client.managment.LabWorkService;
import client.managment.UsrInputReceiver;

public class ExecuteScriptCmd implements Command {
    private UsrInputReceiver usrInputReceiver;
    private LabWorkService labWorkService;
    private String filename;
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
        if (arg!=null) {
            this.filename = arg;
        }
    }
    @Override
    public void execute() {
        if (!filename.equals("")&&!filename.equals(null)) {
            this.labWorkService.executeScript(this.filename);
        }
    }
    public static String getName() {return "execute_script";}
}
