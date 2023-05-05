package common.cmd;

import client.managment.LabWorkService;

public class HelpCmd implements Command {
    private LabWorkService labWorkService;
    @Override
    public void setLabWorkService(LabWorkService labWorkService) {
        this.labWorkService=labWorkService;
    }
    @Override
    public void execute() {
        this.labWorkService.help();
    }

    public static String getName() {return "help";}
}
