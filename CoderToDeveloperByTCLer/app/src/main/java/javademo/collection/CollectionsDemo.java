package javademo.collection;

import java.util.ArrayList;
import java.util.Collections;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/12/11
 *     desc   : 集合工具类Collections 常见方法demo
 *     version: 1.0
 * </pre>
 */

public class CollectionsDemo {

    public static void main(String[] args){
        ArrayList nums=new ArrayList();
        nums.add(12);
        nums.add(-5);
        nums.add(8);
        Collections.sort(nums);//[-5, 8, 12]
        System.out.println(nums);
        nums.add(7);
        Collections.reverse(nums);
        System.out.println(nums);//[7, 12, 8, -5]
        Collections.shuffle(nums);//随机排序
        System.out.println(nums);//假定此刻为：[12, 7, -5, 8]
        nums.add(10);
        System.out.println(nums);//[12, 8, 7, -5, 10]
        Collections.rotate(nums, 3);
        //rotate操作，正数是将nums的后3个数整体搬移到前面，负数是将前面3个数整体搬移到后面。
        System.out.println(nums);//[7, -5, 10, 12, 8]
        Collections.rotate(nums, -2);
        System.out.println(nums);//[10, 12, 8, 7, -5]
        //查找替换操作
        nums.add(7);
        System.out.println(Collections.max(nums));//12
        System.out.println(Collections.min(nums));//-5
        Collections.replaceAll(nums, 7, 9);//将num中所有7替换为9[10, 12, 8, 9, -5, 9]
        System.out.println(nums);
        System.out.println(Collections.frequency(nums, 9));//2
        Collections.sort(nums);//只有排序了才能用二分查找
        System.out.println(Collections.binarySearch(nums, 10));//4
    }
}

/**运行结果：
 [-5, 8, 12]
 [7, 12, 8, -5]
 [7, 12, -5, 8]
 [7, 12, -5, 8, 10]
 [-5, 8, 10, 7, 12]
 [10, 7, 12, -5, 8]
 12
 -5
 [10, 9, 12, -5, 8, 9]
 2
 4
 */