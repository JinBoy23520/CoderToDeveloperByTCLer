package javademo.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javademo.entity.Emp;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/04/16
 *     desc   : map演示
 *     总结   ：
 *     1.HashMap 是一个散列表，是存放一对值的最大接口，即接口中的每一个元素都是一对，以key->value键值对的形式保存
 *     2.四种遍历
 *     3.TreeMap 延伸阅读 http://blog.csdn.net/chenssy/article/details/26668941
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
        HashMap hm = new HashMap();

        Emp emp1 = new Emp("s001", "aaa", 3.5f);
        Emp emp2 = new Emp("s002", "老桑", 3.5f);
        Emp emp3 = new Emp("s003", "老王", 3.5f);


        //将emp放到hm中
        hm.put("s001", emp1);
        hm.put("s002", emp2);
        hm.put("s003", emp3);   // 注意键值不能重复 这时候s002表示老王了
        if (hm.containsKey("s002")) {
            System.out.println("有该员工");
            //如何去除键值
            Emp emp = (Emp) hm.get("s002");
            System.out.println("名字：" + emp.getName());
        } else {
            System.out.println("没有该员工");
        }
        //遍历HashMap中所有的key和value   去除的值没有顺序 Iterator迭代器用于遍历
        Iterator it = hm.keySet().iterator();
        // hasNext返回一个boolean
        while (it.hasNext()) {
            String key = it.next().toString();
            //通过key去除vale
            Emp emp = (Emp) hm.get(key);
            System.out.println("名字" + emp.getName());
            System.out.println("薪水：" + emp.getSal());
        }


        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");
        System.out.println("通过Map.keySet遍历key和value：");
        for (String key : map.keySet()) {
            System.out.println("key= " + key + " and value= " + map.get(key));
        }

        //第二种
        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第三种：推荐，尤其是容量大时
        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第四种
        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }
    }
    //演示HashTable
//        Hashtable ht=new Hashtable();

}

/**
 * 运行结果
 * 有该员工
 * 名字：老桑
 * 名字老王
 * 薪水：3.5
 * 名字老桑
 * 薪水：3.5
 * 名字aaa
 * 薪水：3.5
 * 通过Map.keySet遍历key和value：
 * key= 1 and value= value1
 * key= 2 and value= value2
 * key= 3 and value= value3
 * 通过Map.entrySet使用iterator遍历key和value：
 * 通过Map.entrySet遍历key和value
 * key= 1 and value= value1
 * key= 2 and value= value2
 * key= 3 and value= value3
 * 通过Map.values()遍历所有的value，但不能遍历key
 * value= value1
 * value= value2
 * value= value3
 **/











