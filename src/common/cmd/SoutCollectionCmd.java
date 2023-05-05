package common.cmd;

import client.managment.LabWorkService;

public class SoutCollectionCmd implements Command {
    private LabWorkService labWorkService;
    @Override
    public void setLabWorkService(LabWorkService labWorkService) {
        this.labWorkService=labWorkService;
    }
    @Override
    public void execute() {
        this.labWorkService.soutCollection();
    }
    public static String getName() {return "show";}
}
