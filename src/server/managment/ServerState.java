package server.managment;

import java.util.Scanner;

public class ServerState {
    private static Mode md;
    private static Scanner sc;

    public static void setMode(Mode mode) {
        md = mode;
    }

    public static void setScanner(Scanner scanner) {
        sc = scanner;
    }

    public static Mode getMode() {
        return md;
    }

    public static Scanner getScanner() {
        return sc;
    }
}
