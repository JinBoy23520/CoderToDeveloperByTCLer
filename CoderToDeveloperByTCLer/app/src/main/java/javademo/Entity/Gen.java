package javademo.Entity;

import java.lang.reflect.Method;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/04/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class Gen<T> {
    private T o;

    // 得到T的类型和名称
    public Gen(T a) {
        o = a;
    }

    public void showTypeName() {
        System.out.println("类型是：" + o.getClass().getName());
        //通过反射机制可以得到T类型的很多信息（比如说得到成员函数名）
        Method[] m = o.getClass().getDeclaredMethods();
        //  打印
        for (int i = 0; i < m.length; i++) {
            System.out.println(m[i].getName());
        }
    }
}
