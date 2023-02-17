package net;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

// 网络客户端
public class Intnet {
    public static void main(String[] args) throws IOException {
        System.out.println("客户端已启动。。。");
        // 1 创建套接字，指定服务器的IP和端口号
        Socket socket=new  Socket("127.0.0.1",8888);
        // 2 发送数据，输出流
        OutputStream os=socket.getOutputStream();
        //DataOutputStream dos=new DataOutputStream(os);
        //dos.writeUTF("你好");

        Scanner sc=new Scanner(System.in);
        System.out.println("请输入账号：");
        String name=sc.next();

        System.out.println("请输入密码：");
        String pwd=sc.next();
        User user=new User(name,pwd);
        // 输出到服务器
        ObjectOutputStream oos=new ObjectOutputStream(os);
        oos.writeObject(user);


        // 接收服务端的相应信息,使用输入流
        InputStream is= socket.getInputStream();
        DataInputStream dis=new DataInputStream(is);
        //String str=dis.readUTF();
        //System.out.println("接收到服务端的信息："+str);
        Boolean flag=dis.readBoolean();
        if (flag){
            System.out.println("恭喜，登录成功");
        }else {
            System.out.println("恭喜，登录失败");
        }

        // 关闭流，关闭网络资源
        dis.close();
        is.close();
        oos.close();
        //dos.close();
        os.close();
        socket.close();

    }
}
