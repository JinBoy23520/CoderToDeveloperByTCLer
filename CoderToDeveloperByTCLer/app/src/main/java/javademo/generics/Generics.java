package javademo.generics;


import java.util.ArrayList;

import javademo.entity.Bird;
import javademo.entity.Dog;
import javademo.entity.Gen;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/04/16
 *     desc   : 泛型演示
 *     总结   ; 1.Class<T>在实例化的时候，T要替换成具体类
 *                Class<?>它是个通配泛型，?可以代表任何类型
 *              2.<? extends T>受限统配，表示T的一个未知子类。
 *                <? super T>下限统配，表示T的一个未知父类。
 *              3.泛型体现java反射，获取类方法 属性信息
 *              4.便于代码的复用，减少代码量
 *     version: 1.0
 * </pre>
 */

public class Generics {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ArrayList<Dog> al = new ArrayList<Dog>();   // al 的类型就与 Dog绑定了
        //创建一只狗
        Dog dog1 = new Dog();
        //放入到集合中
        al.add(dog1);
        //	Dog temp=(Dog)al.get(0);
        //	Cat temp=(Cat)al.get(0);

        //	Dog temp=al.get(0);   //取al里中的第一个元素 不用强制类型转换
        Gen<Bird> gen1 = new Gen<Bird>(new Bird());
        gen1.showTypeName();
        getData(al);
    }
    public static void getData(ArrayList<?> data) {
        System.out.println("data :" + data.get(0));
    }
}

    /**
     * 输出
     类型是：javademo.generics.Bird
     count
     test1
     data :javademo.generics.Dog@7d4991ad
    */