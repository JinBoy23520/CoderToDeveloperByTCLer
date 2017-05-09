package javademo.entity;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/04/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class Cat extends Animal {
    private String color;
    private String age;
    private String name;

    public Cat(){

    }
    public Cat(String name , String age, String color){
        this.age = age;
        this.color = color;
        this.name = name;
    }


    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getAge() {
        return age;
    }

    @Override
    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
