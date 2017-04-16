package javademo.collection;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import javademo.Entity.Emp;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/04/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MapDemo {
    public static void main(String[] args) {
		// TODO Auto-generated method stub

	    //LinkList
//		LinkedList ll=new LinkedList();
//		Emp emp1=new Emp("sa01","aa", 1.2f);
//		Emp emp2=new Emp("sa01","bb", 1.2f);
//		//表示把emp1加在链表的最前面
//		ll.addLast(emp1);
//		ll.addFirst(emp2);
//		for(int i=0; i<ll.size(); i++)
//		{
//			System.out.println(((Emp)ll.get(i)).getName());
//		}

        // Vector的用法
//		Vector vv=new Vector();
//		Emp emp1=new Emp("sa01","aa", 1.2f);
//		vv.add(emp1);
//		for(int i=0; i<vv.size(); i++)
//		{
//			Emp emp=(Emp)vv.get(i);
//		}
        //	Stack stack=new Stack();
        //	stack.





		//创建一个HashMap 对象
		HashMap hm=new HashMap();

		Emp emp1=new Emp("s001","aaa", 3.5f);
		Emp emp2=new Emp("s002","老桑", 3.5f);
		Emp emp3=new Emp("s003","老王", 3.5f);

		//将emp放到hm中
		hm.put("s001", emp1);
		hm.put("s002", emp2);
		hm.put("s003", emp3);   // 注意键值不能重复 这时候s002表示老王了
   		if(hm.containsKey("s002"))
		{
			System.out.println("有该员工");
			//如何去除键值
			Emp emp=(Emp)hm.get("s002");
			System.out.println("名字："+emp.getName());
		}
		else
		{
			System.out.println("没有该员工");
		}
		//遍历HashMap中所有的key和value   去除的值没有顺序
		Iterator it=hm.keySet().iterator();
		// hasNext返回一个boolean
		while(it.hasNext())
		{
			String key=it.next().toString();
			//通过key去除vale
			Emp emp=(Emp)hm.get(key);
			System.out.println("名字"+emp.getName());
			System.out.println("薪水："+emp.getSal());
		}

        //演示HashTable
//        Hashtable ht=new Hashtable();

    }
}
