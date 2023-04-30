package client_proxy.managment;
import client_proxy.cmd.Command

public class CommandExecutor {
    public void setCommand(Command command) {
        Collections.getHistory().add(command);
        return command.execute();
    }
}
