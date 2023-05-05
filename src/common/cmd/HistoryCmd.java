package common.cmd;

import client.managment.LabWorkService;

public class HistoryCmd implements Command {
    private LabWorkService labWorkService;
    @Override
    public void setLabWorkService(LabWorkService labWorkService) {
        this.labWorkService=labWorkService;
    }
    @Override
    public void execute() {
        this.labWorkService.history();
    }
    public static String getName() {return "history";}
}
