package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// 网络服务端
public class ServeIntnet {
    public static void main(String[] args) throws IOException {
        System.out.println("服务端已启动。。。");

        try {
            // 1创建套接字，指定服务器的端口号,服务器自己的IP肯定知道，客户端是不知道的，所以需要指定IP
            ServerSocket serverSocket = new ServerSocket(8888);
            // 2等待客户端发来的信息
            // 阻塞方法，接收到了数据，程序就向下执行
            // 循环监听客户端
            int count=0;
            while (true) {

                Socket s = serverSocket.accept(); // 返回一个Socket
                // 每个线程处理一个请求
                new SocketThread(s).start();
                count++;
                System.out.println("这是第"+count+"个请求,ip地址为："+s.getInetAddress());

            }
        } catch (Exception e) {

        }


    }
}
