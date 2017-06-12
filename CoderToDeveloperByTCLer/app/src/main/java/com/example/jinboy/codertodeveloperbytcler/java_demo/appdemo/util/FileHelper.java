package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.util;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/06/11
 *     desc   : 文件协助类
 *              1.文件操作模式：
 *              1.Context.MODE_PRIVATE：私有操作模式，默认模式，代表该文件是私有数据，只能被应用本身访问，
 *              在该模式下，写入的内容会覆盖源文件的内容，如果想把新写入的内容追加到原文件中，可以使用Context.MODE_APPEND
                2.Context.MODE_APPEND：追加操作模式：模式会检查文件是否存在，存在就往文件追加内容，否则就创建新文件
                3.Context.MODE_WORLD_READABLE：表示当前文件可以被其他应用读取。
                4.Context.MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入。
                如果希望文件被其他应用读和写，可以传入：
                openFileOutput("1234.txt", Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
                另外，文件默认放在/data/data//files目录下
                对于文件的获取，Android中提供了getCacheDir()和getFilesDir()方法：
                getCacheDir()方法用于获取/data/data//cache目录
                getFilesDir()方法用于获取/data/data//files目录
 *     version: 1.0
 * </pre>
 */

public class FileHelper {

    private Context mContext;

    public FileHelper() {
    }

    public FileHelper(Context mContext) {
        super();
        this.mContext = mContext;
    }

    /**
     * 这里定义的是一个文件保存的方法，写入到文件中，所以是输出流
     **/
    public void save(String filename, String filecontent) throws Exception {
        //这里我们使用私有模式,创建出来的文件只能被本应用访问,还会覆盖原文件哦
        FileOutputStream output = mContext.openFileOutput(filename, Context.MODE_PRIVATE);
        output.write(filecontent.getBytes());  //将String字符串以字节流的形式写入到输出流中
        output.close();         //关闭输出流
    }


    /**
     * 这里定义的是文件读取的方法
     */
    public String read(String filename) throws IOException {
        //打开文件输入流
        FileInputStream input = mContext.openFileInput(filename);
        byte[] temp = new byte[1024];
        StringBuilder sb = new StringBuilder("");
        int len = 0;
        //读取文件内容:
        while ((len = input.read(temp)) > 0) {
            sb.append(new String(temp, 0, len));
        }
        //关闭输入流
        input.close();
        return sb.toString();
    }
}
