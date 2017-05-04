package javademo.iodemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/05/02
 *     desc   :  演示FileInputStream
 *              1:把文件读入到计算机内存中
 *     version: 1.0
 * </pre>
 */
public class FileInputStreamDemo {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        File f = new File("C:/Users/Administrator/Desktop/aa.txt");
        FileInputStream fis = null;
        //因为File没有读功能，所以需要一个InputStream
        try {
            fis = new FileInputStream(f);
            //定义一个字节数组
            byte[] bytes = new byte[1024];
            //循环读取
            int n = 0;
            //读完返回 -1
            while ((n = fis.read(bytes)) != -1) {
                //把字节数组中的内容转换成字符串
                String s = new String(bytes, 0, n);
                //输出字符串中的内容
                System.out.println(s);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭文件流必须关闭
            try {
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
