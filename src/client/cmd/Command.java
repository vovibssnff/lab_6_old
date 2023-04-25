package client.cmd;


import client.io.Mode;

import java.util.Scanner;

public interface Command {

    default void execute(String arg, Scanner scanner, Mode mode) {

    }
    default boolean validate(String arg) {return true;}
}
