package javademo.collection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javademo.Entity.Emp;
import javademo.Entity.EmpManage;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/04/16
 *     desc   :实现雇员管理添加·删除·修改功能
 *     version: 1.0
 * </pre>
 */

public class ArrayListDemo {
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        //创建一个EmpManage对象
        EmpManage em = new EmpManage();
        //做出一个简单的菜单
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("请选择一个操作:");
            System.out.println("1:表示添加一个雇员");
            System.out.println("2：雇员查找一个:");
            System.out.println("3:修改一个雇员");
            System.out.println("4：删除一个雇员");
            System.out.println("5：退出");
            String operType = br.readLine();
            if (operType.equals("1")) {
                System.out.println("请输入编号");
                String empNo = br.readLine();
                System.out.println("请输入名字");
                String name = br.readLine();
                System.out.println("请输入工资");
                float sal = Float.parseFloat(br.readLine());
                Emp emp = new Emp(empNo, name, sal);
                em.addEmp(emp);
            } else if (operType.equals("2")) {
                System.out.println("请输入编号");
                String empNo = br.readLine();
                em.showInfo(empNo);
            } else if (operType.equals("3")) {
                System.out.println("请输入编号");
                String empNo = br.readLine();
                System.out.println("请输入修改后工资");
                float sal = Float.parseFloat(br.readLine());
                em.updateSal(empNo, sal);
            } else if (operType.equals("4")) {
                System.out.println("请输入编号");
                String empNo = br.readLine();
                em.delEmp(empNo);
            } else if (operType.equals("5")) {
                //退出
                System.exit(0);
            }
        }
    }
}




