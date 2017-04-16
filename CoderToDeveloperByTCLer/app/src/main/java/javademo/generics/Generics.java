package javademo.generics;

import java.io.FileReader;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/04/16
 *     desc   : 异常演示
 *     version: 1.0
 * </pre>
 */

public class Generics {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //检查异常1.打开文件
        FileReader fr=null;
        try {
            fr=new FileReader("d:\\aa.text");
            //在出现异常的地方，下面的代码的就不执行
            System.out.println("aaa");
        } catch (Exception e) {
            // TODO: handle exceptionS
            System.exit(-1);
            System.out.println("message="+e.getLocalizedMessage());  //没有报那一行出错
            e.printStackTrace();   // 打印出错异常还出现可以报出错先异常的行
        }
        //这个语句块不管发生没有发生异常，都会执行
        //一般来说，把需要关闭的资源，文件，连接，内存等
        finally
        {
            System.out.println("进入finally");
            if(fr!=null);
            {
                try {
                    fr.close();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }
        System.out.println("OK");


        //连接一个192.168.12.12 ip端口号45678
        //
        // 运行异常  分母为0
        //	int a=4/0;
        // 数组越界
/*		try {
			int arr[]={1,2,3};
		System.out.println(arr[123]);

		} catch (Exception e) {
			// TODO: handle exception
		}  */

    }
}
