package day27ts;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTS {
    /*
    1. IP:唯一的标识Internet上的计算机(通信实体)
    2.在Java中使用InetAddress类代表IP
    3.IP分类:IPv4和IPv6;万维网和局域网
    4.域名: www.baidu.com / www.mi.com / www.sina.com / www.jd.com
    5.本地回路地址:127.0.0.1对应者:Localhost
    6.如何实例化InetAddress:两个方法:getByname(Stringhost), getLocalHos
    两个常用方法:getHostname()/getHostAddress()
    7.端口号:正在计算机上运行的进程。
        要求:不同的进程有不同的端口号
        范围:被规定为一个16位的整数0~65535。
    8.端口号与IP地址的组合得出一个网络套接字 Socket
     */
    public static void main(String[] args) {
        try {
            InetAddress inet1 = InetAddress.getByName("192.168.10.24");
            System.out.println(inet1);

            InetAddress inet2 = InetAddress.getByName("www.google.com");
            System.out.println(inet2);

            InetAddress inet3 = InetAddress.getByName("127.0.0.1");
            System.out.println(inet3);

            InetAddress inet4 = InetAddress.getLocalHost();
            System.out.println(inet4);

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
