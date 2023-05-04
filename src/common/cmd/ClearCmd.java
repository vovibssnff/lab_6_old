package common.cmd;

import client.managment.LabWorkService;

public class ClearCmd implements Command {
    private LabWorkService labWorkService;
    @Override
    public void setLabWorkService(LabWorkService labWorkService) {
        this.labWorkService=labWorkService;
    }
    @Override
    public void execute() {
        this.labWorkService.clear();
    }
    public static String getName() {return "clear";}
}
