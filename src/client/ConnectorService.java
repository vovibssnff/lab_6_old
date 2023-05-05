package client;

import client.managment.ProgramState;
import com.google.gson.Gson;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ConnectorService {
    int nextSequenceNumber = 0;
    Map<Integer>
    public static void send(String request) {

        DatagramSocket socket = new DatagramSocket();
        String jsonRequest = ProgramState.getGson().toJson(request);
        byte[] requestData = jsonRequest.getBytes();
        DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, InetAddress.getLocalHost(), 8080);
        socket.send(requestPacket);

        Gson gson = ProgramState.getGson();
    }
}
