package client;

import client.managment.ProgramState;
import com.google.gson.Gson;

import java.net.DatagramSocket;

public class ConnectorService {
    public static void send(String message) {
        DatagramSocket socket = new DatagramSocket();

        Gson gson = ProgramState.getGson();
    }
}
