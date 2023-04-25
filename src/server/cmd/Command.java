package server.cmd;


import server.io.Mode;

import java.util.Scanner;

public interface Command {

    default void execute(String arg, Scanner scanner, Mode mode) {

    }
}
