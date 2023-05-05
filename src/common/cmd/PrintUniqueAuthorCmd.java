package common.cmd;

import client.managment.LabWorkService;

public class PrintUniqueAuthorCmd implements Command {
    private LabWorkService labWorkService;
    @Override
    public void setLabWorkService(LabWorkService labWorkService) {
        this.labWorkService=labWorkService;
    }
    @Override
    public void execute() {
        this.labWorkService.printUniqueAuthor();
    }
    public static String getName() {return "print_unique_authors";}
}
