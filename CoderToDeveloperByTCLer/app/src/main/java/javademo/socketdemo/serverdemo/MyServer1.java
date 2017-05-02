package javademo.socketdemo.serverdemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/04/25
 *     desc   : 服务器半双工通信Demo
 *     version: 1.0
 * </pre>
 */

public class MyServer1 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyServer1 msl=new MyServer1();
    }
    public MyServer1() {
        try {
            //在9999号端口监听
            ServerSocket ss=new ServerSocket(9999);
            System.out.println("我是服务器，我正在监听");
            //等待摸个客户来连接，该函数会返回一个Scokey连接
            Socket s=ss.accept();

            //要读取s中传递的数据
            InputStreamReader isr=new InputStreamReader(s.getInputStream());
            BufferedReader br=new BufferedReader (isr);
            //行读取
            String info=br.readLine();

            System.out.println("服务器已经收到，"+info);

            PrintWriter pw=new PrintWriter(s.getOutputStream(), true);

            pw.println("你好吗，我是服务器");


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
