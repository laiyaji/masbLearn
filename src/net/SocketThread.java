package net;

import java.io.*;
import java.net.Socket;

public class SocketThread extends Thread{
    private Socket s;

    public SocketThread(Socket socket){
        this.s=socket;
    }
    // 通过Socket   来获取对象流输入输出数据
    InputStream inputStream= null;
    DataInputStream dis=null;
    OutputStream os=null;
    DataOutputStream dos=null;
    ObjectInputStream ois=null;
    @Override
    public void run() {


        try {
            inputStream = s.getInputStream();
             dis=new DataInputStream(inputStream);
            // 感受到客户端发送的数据，获取数据就是用输入流
             ois=new ObjectInputStream(inputStream);
            User user=(User)ois.readObject();

            // 标记是否登录成功
            Boolean flag=false;
            if (user.getName().equals("娜娜")&&user.getPwd().equals("123123")){
                flag=true;
            }

            // 客户端发来的数据需要读取到程序，所以是输入流
            //String str=dis.readUTF();
            //System.out.println("客户端的请求数据为："+str);

            // 向客户端输出一句话
             os=s.getOutputStream();
             dos=new DataOutputStream(os);
            //dos.writeUTF("服务端收到了你发送的数据");
            dos.writeBoolean(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}
