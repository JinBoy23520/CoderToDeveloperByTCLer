package javademo.entity;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/04/16
 *     desc   :雇员类
 *     version: 1.0
 * </pre>
 */

public class Emp {
    //学号
    private String empNo;
    private String name;
    private float sal;

    //构造函数
    public Emp(String empNo, String name, float sal) {
        this.empNo = empNo;
        this.name = name;
        this.sal = sal;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSal() {
        return sal;
    }

    public void setSal(float sal) {
        this.sal = sal;
    }
}

