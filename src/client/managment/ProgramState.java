package client.managment;

import client.io.Mode;

import java.util.Scanner;

public class ProgramState {
    private static Mode md;
    private static Scanner sc;
    private static UsrInputReceiver usrInputReceiver;
    private static LabWorkService labWorkService;

    public static void setMode(Mode mode) {
        md = mode;
    }

    public static void setScanner(Scanner scanner) {
        sc = scanner;
    }

    public static void setUsrInputReceiver(UsrInputReceiver usrInputReceiver) {ProgramState.usrInputReceiver=usrInputReceiver;}
    public static void setLabWorkService(LabWorkService labWorkService) {ProgramState.labWorkService=labWorkService;}
    public static Mode getMode() {
        return md;
    }

    public static Scanner getScanner() {
        return sc;
    }

    public static UsrInputReceiver getUsrInputReceiver() {
        return usrInputReceiver;
    }

    public static LabWorkService getLabWorkService() {
        return labWorkService;
    }
}
