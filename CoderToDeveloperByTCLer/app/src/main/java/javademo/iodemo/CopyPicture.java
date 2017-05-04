package javademo.iodemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/05/02
 *     desc   : 复制照片
 *     version: 1.0
 * </pre>
 */
public class CopyPicture {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //创建输入流
        FileInputStream fis = null;
        //创建输出流
        FileOutputStream fos = null;
        try {
            //用于图片也可以
            fis = new FileInputStream("C:/Users/Administrator/Desktop/a.jpg");
            fos = new FileOutputStream("e:\\a.jpg");
            // 用于文本文件也可以
//            fis = new FileInputStream("C:/Users/Administrator/Desktop/ss.txt");
//            fos = new FileOutputStream("e:\\ss.txt");
            byte buf[] = new byte[1024];
            //循环读取
            int n = 0;//记录实际读取到的字节数
            //循环读取
            while ((n = fis.read(buf)) != -1) {
                //输出到指定文件
                fos.write(buf);
                System.out.println("文件已经完成复制");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭文件流
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
