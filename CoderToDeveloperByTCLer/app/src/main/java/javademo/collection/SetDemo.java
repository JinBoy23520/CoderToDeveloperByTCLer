package javademo.collection;

import java.util.HashSet;
import java.util.Iterator;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/04/18
 *     desc   :Set演示  运用较少
 *     version: 1.0
 * </pre>
 */

public class SetDemo {
    public static void main(String[] args) {
        /**
         * 1，HashSet是无序
         * 2，不循序有重复的值
         */
        HashSet<String> hs=new HashSet<String>();
        hs.add("21221");
//        hs.add("21221");
        hs.add("112");
        hs.add("312");
        hs.add("421");
        hs.add("312");

        /**
         * 用迭代器Iterator遍历集合
         */
        Iterator<String> it=hs.iterator();
        while(it.hasNext()){
            String s=it.next();
            System.out.println(s +" ");
        }
    }
}
    /**
     运行结果：
     112
     421
     312
     21221 **/