package client.connect;

import client.cmd.Command;
import client.data.LabWork;

public class Transmitter {
    private Command command;
    private LabWork labWork;
    private Long id;
    private Double minimalPoint;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void setLabWork(LabWork labWork) {
        this.labWork = labWork;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMinimalPoint(Double minimalPoint) {
        this.minimalPoint = minimalPoint;
    }
}
