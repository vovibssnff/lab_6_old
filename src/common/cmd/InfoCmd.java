package common.cmd;

import client.managment.LabWorkService;

public class InfoCmd implements Command {
    private LabWorkService labWorkService;
    @Override
    public void setLabWorkService(LabWorkService labWorkService) {
        this.labWorkService=labWorkService;
    }
    @Override
    public void execute() {
        this.labWorkService.info();
    }
    public static String getName() {return "info";}
}
