package day27ts;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPtest3 {
    @Test
    public void client() {

        Socket socket = null;
        OutputStream ops = null;
        FileInputStream ips = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 8888);
            ops = socket.getOutputStream();
            ips = new FileInputStream(new File("/Users/zi-wei/Downloads/JPEG影像.jpeg"));
            int end;
            byte[] bytes = new byte[10];
            while ((end = ips.read(bytes)) != -1) {  //read沒有告訴socket停止收取資料。
                ops.write(bytes, 0, end);
            }
            socket.shutdownOutput(); //關閉數據接收
            InputStream inputStream = socket.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((end=inputStream.read(bytes)) != -1){
                baos.write(bytes,0,end);
            }
            System.out.println(baos.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ops != null) {
                try {
                    ops.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ips != null) {
                try {
                    ips.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Test
    public void server() {
        ServerSocket srs = null;
        Socket accept = null;
        InputStream ips = null;
        FileOutputStream fos = null;
        try {
            srs = new ServerSocket(8888);
            accept = srs.accept();
            ips = accept.getInputStream();
            fos = new FileOutputStream(new File("/Volumes/Data/Java/人z.jpeg"));
            byte[] bytes = new byte[10];
            int end;
            while ((end = ips.read(bytes)) != -1) {
                fos.write(bytes, 0, end);
            }
            accept.getOutputStream().write("以收到".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (srs != null) {
                try {
                    srs.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (accept != null) {
                try {
                    accept.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ips != null) {
                try {
                    ips.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }
}
