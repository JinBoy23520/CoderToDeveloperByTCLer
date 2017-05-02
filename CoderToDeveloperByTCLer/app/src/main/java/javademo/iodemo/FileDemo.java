package javademo.iodemo;

import java.io.File;
import java.io.IOException;


/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/05/02
 *     desc   : 功能： File类的功能
 *              1:创建一个文件流，判断文件是否存在如果存不存在就创建新文件，前提必须在文件所在的文件夹必须存在
 *              2：创建文件夹
 * 3            ：把文件夹下面的的文件的信息显示出来，或者递归从文件夹下面找到
 *     version: 1.0
 * </pre>
 */

public class FileDemo {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //创建一个文件对象
//        File f = new File("C:/Users/Administrator/Desktop/aa.txt");
//        //得到文件路径
//        System.out.println("文件路径：" + f.getAbsolutePath());
//        //得到文件的大小
//        System.out.println("文件大小" + f.length());
//        //判断文件是否可读
//        System.out.println("可读" + f.canRead());


//        判断文件是否存在
//		File f=new File("C:/Users/Administrator/Desktop/aa.txt");
//		if(!f.exists())
//		{
//			//创建
//			System.out.println("创建文件");
//			try {
//				f.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}else
//		{
//			System.out.println("文件已经存在");
//		}


		//判断文件夹是否存在
//		File f=new File("C:/Users/Administrator/Desktop/ff");
//		if(f.isDirectory())  //如果文件夹存在
//		{
//			System.out.println("文件夹已经存在");
//		}else{
//			//创建文件夹
//			f.mkdir();
//		}


        //列出一个文件夹下面所有的文件
        File f = new File("C:/Users/Administrator/Desktop");
        if (f.isDirectory()) {
            //创建一个用于存放文件名字的数组
            File lists[] = f.listFiles();
            //打印文件夹下面的文件名字
            for (int i = 0; i < lists.length; i++) {
                //得到文件的名字
                System.out.println("文件名：" + lists[i].getName());
            }
        }
    }
}
