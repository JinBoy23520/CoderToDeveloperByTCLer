package javademo.socketdemo.clientdemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/04/25
 *     desc   : 客户端半双工通信Demo
 *     version: 1.0
 * </pre>
 */

public class MyClient1 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyClient1 mcl=new MyClient1();
    }

    public MyClient1() {

        try {
            //Socket,就是连接某个服务器端，10.68.215.2表示本机的Ip, 9999表示端口号
            Socket s=new Socket("10.68.215.2", 9999);

            //如果s连接成功， 就可以发数据给服务器
            //我们通过pw 向s写数据  true表示刷新流
            PrintWriter pw=new PrintWriter(s.getOutputStream(),true);

            pw.println("你好吗？我是客户端");

            InputStreamReader isr=new InputStreamReader(s.getInputStream());
            BufferedReader br=new BufferedReader (isr);
            //行读取
            String response=br.readLine();

            System.out.println("客户端已经收到，"+response);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
