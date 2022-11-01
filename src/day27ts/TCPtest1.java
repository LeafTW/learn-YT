package day27ts;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPtest1 {
    @Test
    //客戶端
    public void client() {
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            //1.創建socket對象，指明服务器端的ip和端口号
            InetAddress byName = InetAddress.getByName("127.0.0.1");
            socket = new Socket(byName, 678);
            //2.获取一个输出流,用于输出数据
            outputStream = socket.getOutputStream();
            outputStream.write("TCP_test".getBytes());
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
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    //伺服端
    @Test
    public void server() {
        ServerSocket socket = null;
        Socket accept = null;
        InputStream inputStream = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建服务器端的Serversocket,指明自己的端口号
            socket = new ServerSocket(678);
//            2.调用accept()表示接收来自于客户端的socket
            accept = socket.accept();
            //3.獲取輸入流數據
            inputStream = accept.getInputStream();
            byte[] bytes = new byte[5];
            int end;
            //ByteArrayOutputStream 直接搬運數據到ByteArrayOutputStream，避免發生亂碼
            baos = new ByteArrayOutputStream();
            //https://www.youtube.com/watch?v=GEGJuUUM_Zo&list=PLmOn9nNkQxJH0qBIrtV6otI0Ep4o2q67A&index=623&ab_channel=%E5%B0%9A%E7%A1%85%E8%B0%B7IT%E5%9F%B9%E8%AE%AD%E5%AD%A6%E6%A0%A1
            while ((end = inputStream.read(bytes)) != -1) {
                baos.write(bytes, 0, end);
            }
            System.out.println(baos.toString());
            System.out.println(accept.getInetAddress().getAddress());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
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
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
