package common.cmd;

import client.managment.LabWorkService;

public class PrintFieldDescendingMinimalPointCmd implements Command {
    private LabWorkService labWorkService;
    @Override
    public void setLabWorkService(LabWorkService labWorkService) {
        this.labWorkService=labWorkService;
    }
    @Override
    public void execute() {
        this.labWorkService.printFieldDescendingMinimalPoint();
    }
    public static String getName() {return "pfdmp";}
}
