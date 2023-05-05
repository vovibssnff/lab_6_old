package common.cmd;

import client.managment.LabWorkService;

public class HeadCmd implements Command {
    private LabWorkService labWorkService;
    @Override
    public void setLabWorkService(LabWorkService labWorkService) {
        this.labWorkService=labWorkService;
    }
    @Override
    public void execute() {
        this.labWorkService.head();
    }
    public static String getName() {return "head";}
}
