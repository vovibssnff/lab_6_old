package client;

import client.managment.ProgramState;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

//public class ClientConnectionService {
//    int nextSequenceNumber = 0;
//    private static DatagramChannel channel;
//    private static DatagramSocket socket = null;
//    private static String jsonRequest = null;
//    private static byte[] requestData = null;
//    private static DatagramPacket requestPacket = null;
//    private static ByteBuffer buffer = ByteBuffer.allocate(1024);
//
//
//    //Метод, инициализирующий сокет
//    public static void init() {
//        try {
//            socket = new DatagramSocket();
//            channel = DatagramChannel.open();
//            channel.bind(new InetSocketAddress(8081));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//    //Отправка запроса
//    public static String sendRequest(String request) {
//        try {
//            jsonRequest = ProgramState.getGson().toJson(request);
//            requestData = jsonRequest.getBytes();
//            requestPacket = null;
//            requestPacket = new DatagramPacket(requestData, requestData.length, InetAddress.getLocalHost(), 8080);
//            socket.send(requestPacket);
//            return getResponse();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return "Connection error";
//    }
//    //Получение ответа
//    public static String getResponse() {
//
//        try {
//
//            buffer.clear();
//            channel.receive(buffer);
//            buffer.flip();
//            String jsonResponse = new String(buffer.array(), 0, buffer.limit());
//            return ProgramState.getGson().fromJson(jsonResponse, String.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "empty_string";
//    }
//}

public class ClientConnectionService {
    private static DatagramChannel channel;
    private static DatagramSocket socket;
    private static ByteBuffer buffer = ByteBuffer.allocate(1024);

    private static int nextSequenceNumber = 0;
    private static boolean isAckReceived = false;

    public static void init() {
        try {
            socket = new DatagramSocket();
            channel = DatagramChannel.open();
            channel.bind(new InetSocketAddress(8081));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String sendRequest(String request) {
        try {
            String jsonRequest = ProgramState.getGson().toJson(request);
            byte[] requestData = jsonRequest.getBytes();
            DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, InetAddress.getLocalHost(), 8080);

            // Send packet and wait for acknowledgement
            isAckReceived = false;
            while (!isAckReceived) {
                socket.send(requestPacket);
                return waitForAck();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Connection error";
    }

    private static String waitForAck() throws IOException {
        buffer.clear();
        channel.receive(buffer);
        buffer.flip();
        int ackSequenceNumber = buffer.getInt(); // Assuming the first 4 bytes of the packet is the sequence number
        if (ackSequenceNumber == nextSequenceNumber) {
            isAckReceived = true;
            String jsonResponse = new String(buffer.array(), 4, buffer.limit() - 4);
            buffer.clear();
            return ProgramState.getGson().fromJson(jsonResponse, String.class);
        } else {
            return "No response, error";
        }
    }

}

