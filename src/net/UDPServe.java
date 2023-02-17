package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServe {
    public static void main(String[] args) throws IOException {

        System.out.println("老师上线了。。。");
        DatagramSocket ds = new DatagramSocket(9999);
        byte[] b = new byte[1024];
        // 空数据包接收数据
        DatagramPacket dp = new DatagramPacket(b, b.length);
        // 接收对方的数据包
        ds.receive(dp);

        // 取出数据
        byte[] bytes = dp.getData();
        String str = new String(bytes,0,dp.getLength());
        System.out.println("学生对我说："+str);

        // 老师回复
        // 2 准备数据包
        String s="同学，你也好";
        byte [] by=s.getBytes();
        DatagramPacket dp1 =new DatagramPacket(by,by.length, InetAddress.getByName("127.0.0.1"),  8888);

        ds.send(dp1);


        ds.close();

    }
}
