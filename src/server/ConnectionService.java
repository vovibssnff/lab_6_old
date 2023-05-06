package server;

import server.managment.ServerState;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ConnectionService {

    private static ByteBuffer buffer = ByteBuffer.allocate(1024);

    public static void connect() {
        DatagramChannel channel = null;
        try {
            channel = DatagramChannel.open();
            channel.bind(new InetSocketAddress(8080));
            ByteBuffer buffer = ByteBuffer.allocate(1024);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            buffer.clear();
            try {
                channel.receive(buffer);
                buffer.flip();
                String jsonRequest = new String(buffer.array(), 0, buffer.limit());
                System.out.println(ServerState.getGson().fromJson(jsonRequest, String.class));
                String responce = "abobus_sus_amogus";
                String jsonResponce = ServerState.getGson().toJson(responce);
                buffer.clear();
                buffer.put(jsonResponce.getBytes());
                buffer.flip();
                channel.send(buffer, new InetSocketAddress("localhost", 8081));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
