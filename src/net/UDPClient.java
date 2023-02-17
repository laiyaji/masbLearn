package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws IOException {

        System.out.println("学生上线了。。。");
        // 1 准备套接字
        DatagramSocket ds=new DatagramSocket(8888);

        // 2 准备数据包
        String str="你好";
        byte [] b=str.getBytes();
        DatagramPacket dp=new DatagramPacket(b,b.length, InetAddress.getByName("127.0.0.1"),  9999);

        // 发送
        ds.send(dp);


        // 接收老师发送回来的信息
        byte[] bt = new byte[1024];
        // 空数据包接收数据
        DatagramPacket dp2 = new DatagramPacket(bt, bt.length);
        // 接收对方的数据包
        ds.receive(dp2);

        // 取出数据
        byte[] bytes = dp2.getData();
        String tr = new String(bytes,0,dp2.getLength());
        System.out.println("老师对我说："+tr);

        ds.close();
    }

}
