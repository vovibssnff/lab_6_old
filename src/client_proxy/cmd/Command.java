package client_proxy.cmd;


import client_proxy.io.Mode;

import java.util.Scanner;

public interface Command {

    default void execute(String arg) {}
    default boolean validate(String arg) {return true;}
}
