package javademo.generics;


import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/04/16
 *     desc   : 泛型演示
 *     version: 1.0
 * </pre>
 */

public class Generics {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ArrayList<Dog> al=new ArrayList<Dog>();   // al 的类型就与 Dog绑定了
        //创建一只狗
        Dog dog1=new Dog();
        //放入到集合中
        al.add(dog1);
        //	Dog temp=(Dog)al.get(0);
        //	Cat temp=(Cat)al.get(0);

        //	Dog temp=al.get(0);   //取al里中的第一个元素 不用强制类型转换
        Gen<Bird> gen1=new Gen<Bird>(new Bird());
        gen1.showTypeName();
    }

}
//定义一个个Bird
class Bird
{
    public void test1()
    {
        System.out.println("aa");
    }
    public void count(int a, int b)
    {
        System.out.println(a+b);
    }
}
//定义一个类
class Gen<T>
{
    private T o;
    // 得到T的类型和名称
    public Gen(T a)
    {
        o=a;
    }
    public void showTypeName()
    {
        System.out.println("类型是："+o.getClass().getName());
        //通过反射机制可以得到T类型的很多信息（比如说得到成员函数名）
        Method[]m=o.getClass().getDeclaredMethods();
        //  打印
        for(int i=0; i<m.length; i++)
        {
            System.out.println(m[i].getName());
        }
    }

}
class Cat
{
    private String color;
    private int age;
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
class Dog
{
    private String name;
    private int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
