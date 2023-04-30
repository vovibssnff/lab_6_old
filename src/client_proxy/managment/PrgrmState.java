package client.managment;

import client.io.Mode;
import java.util.Scanner;

public class PrgrmState {
    private static Scanner sc;
    private static Mode mode;

    public static void setScanner(Scanner sc) {
        PrgrmState.sc = sc;
    }

    public static void setMode(Mode mode) {
        PrgrmState.mode = mode;
    }

    public static Scanner getScanner() {
        return sc;
    }

    public static Mode getMode() {
        return mode;
    }
}
