package client;

import client.managment.ProgramState;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ConnectorService {
    int nextSequenceNumber = 0;
    private static DatagramChannel channel;
    public static String sendRequest(String request) {

        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            String jsonRequest = ProgramState.getGson().toJson(request);
            byte[] requestData = jsonRequest.getBytes();
            DatagramPacket requestPacket = null;
            requestPacket = new DatagramPacket(requestData, requestData.length, InetAddress.getLocalHost(), 8080);
            socket.send(requestPacket);
            return getResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Connection error";
    }
    public static String getResponse() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            channel = DatagramChannel.open();
            channel.bind(new InetSocketAddress(8081));
            buffer.clear();
            channel.receive(buffer);
            buffer.flip();
            String jsonResponse = new String(buffer.array(), 0, buffer.limit());
            return ProgramState.getGson().fromJson(jsonResponse, String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "empty_string";
    }
}
