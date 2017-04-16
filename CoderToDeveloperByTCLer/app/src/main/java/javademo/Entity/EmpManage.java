package javademo.Entity;

import java.util.ArrayList;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/04/16
 *     desc   :雇员管理员类
 *     version: 1.0
 * </pre>
 */

public class EmpManage {
    private ArrayList al=null;
    public EmpManage()
    {
        al=new ArrayList();
    }
    //加入员工
    public void addEmp(Emp emp)
    {
        al.add(emp);
    }
    //显示员工的相关信息
    public void showInfo(String empNo)
    {
        //遍历整个ArrayList()
        for (int i=0; i<al.size(); i++)
        {
            //去除Emp对象
            Emp emp=(Emp)al.get(i);
            //比较编号
            if(emp.getEmpNo().equals(empNo))
            {
                System.out.println("找到员工，他的信息是：");
                System.out.println("编号是"+empNo);
                System.out.println("名字是"+emp.getName());
                System.out.println("工资是"+emp.getSal());

            }
        }
    }

    //修改工资
    public void updateSal(String empNo,float newSal)
    {
        for(int i=0; i<al.size(); i++)
        {
            Emp emp=(Emp)al.get(i);
            if(emp.getEmpNo().equals(empNo))
            {
                emp.setSal(newSal);
            }
        }
    }
    public void delEmp(String empNo)
    {
        for(int i=0; i<al.size(); i++)
        {
            Emp emp=(Emp)al.get(i);
            if(emp.getEmpNo().equals(empNo))
            {
                al.remove(i);
            }
        }
    }
}
