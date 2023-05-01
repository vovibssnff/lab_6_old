package common.cmd;


public interface Command {

    default void execute() {}
    default boolean validate(String arg) {return true;}
    default Command cast(Command command) {
        try {
            return (Command) command.clone();
        } catch (CloneNotSupportedException e) {
            e.getMessage();
        }
        return command;
    }
}
