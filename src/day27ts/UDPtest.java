package day27ts;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPtest {
    @Test
    public void sender() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();

            byte[] bytes = "UDP執行".getBytes();
            InetAddress localHost = InetAddress.getLocalHost();
            DatagramPacket packet = new DatagramPacket(bytes,0, bytes.length, localHost, 8899);

            socket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    @Test
    public void sever() {
        DatagramSocket dgs = null;
        try {
            dgs = new DatagramSocket(8899);

            byte[] bytes = new byte[100];
            DatagramPacket dgp = new DatagramPacket(bytes, 0, bytes.length);
            dgs.receive(dgp);

            System.out.println(new String(dgp.getData(), 0,dgp.getLength()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (dgs != null) {
                dgs.close();
            }
        }
    }
}
