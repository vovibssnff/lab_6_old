package client;


import java.io.IOException;
import java.net.InetSocketAddress;

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

//TODO просто отметка между версиями клиентов

//public class ClientConnectionService {
//    private static DatagramChannel channel;
//    private static DatagramSocket socket;
//    private static ByteBuffer buffer = ByteBuffer.allocate(1024);
//
//    private static int nextSequenceNumber = 0;
//    private static boolean isAckReceived = false;
//    private static Integer count=12;
//
//    public static void init() {
//        try {
//            socket = new DatagramSocket();
//            channel = DatagramChannel.open();
//            channel.bind(new InetSocketAddress(8081));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static String sendRequest(String request) {
//        count++;
//        try {
//            String jsonRequest = count + ProgramState.getGson().toJson(request);
//            byte[] requestData = jsonRequest.getBytes();
//            DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, InetAddress.getLocalHost(), 8080);
//
//            // Send packet and wait for acknowledgement
//            isAckReceived = false;
//            while (!isAckReceived) {
//                socket.send(requestPacket);
//                return waitForAck();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return "Connection error";
//    }
//
//    private static String waitForAck() throws IOException {
//        buffer.clear();
//        channel.receive(buffer);
//        buffer.flip();
//        int ackSequenceNumber = buffer.getInt(); // Assuming the first 4 bytes of the packet is the sequence number
//        if (ackSequenceNumber == nextSequenceNumber) {
//            isAckReceived = true;
//            String jsonResponse = new String(buffer.array(), 4, buffer.limit() - 4);
//            buffer.clear();
//            return ProgramState.getGson().fromJson(jsonResponse, String.class);
//        } else {
//            return "No response, error";
//        }
//    }
//
//}

public class ClientConnectionService {
    ReliableSocket server;
    public UDPtestc() throws IOException {
        server = new ReliableSocket();
        server.connect(new InetSocketAddress("127.0.0.1", 9876));
        byte[] buffer = new byte[1024];
        int count,progress=0;
        InputStream in = server.getInputStream();
        while((count=in.read(buffer)) >0){
            progress+=count;
            System.out.println(""+progress);
        }
        server.close();
    }

    public static void main(String[] args) throws IOException {
        new UDPtestc();
    }

}