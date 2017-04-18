package javademo.collection;

import java.util.LinkedList;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/04/18
 *     desc   : LinkedList演示
 *     总结 ：
 *     1.LinkedList方法上比ArrayList多了对头部尾部的索引添加删除方法（LinkedList基于链表的数据结构）
 *     2.Java中ArrayList和LinkedList区别:http://pengcqu.iteye.com/blog/502676
 *     3.空间复杂度：ArrayList的空间浪费主要体现在在list列表的结尾预留一定的容量空间，
 *     而LinkedList的空间花费则体现在它的每一个元素都需要消耗相当的空间
 *     4.时间复杂度：LinkedList不支持高效的随机元素访问
 *     version: 1.0
 * </pre>
 */

public class LinkedListDemo {
    private static void pringLinkedList(LinkedList<String> linkedList){
        System.out.println("当前元素的集合：");
        for(int i=0;i<linkedList.size();i++){
            System.out.print(linkedList.get(i)+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList<String> linkedList=new LinkedList<String>();
//        linkedList.addFirst("张三");
        linkedList.add("张三");
        linkedList.add("李四");
        linkedList.add("王五");
        linkedList.add("李四");
        linkedList.add("赵六");
        pringLinkedList(linkedList);

        // indexOf 寻找位置
        System.out.println(linkedList.indexOf("李四"));
        pringLinkedList(linkedList);

        // peekFirst 获取第一个元素
        System.out.println(linkedList.peekFirst());
        pringLinkedList(linkedList);

        // peekLast 获取最后一个元素
        System.out.println(linkedList.peekLast());
        pringLinkedList(linkedList);

        // pollFirst 摘取第一个元素
        System.out.println(linkedList.pollFirst());
        pringLinkedList(linkedList);

        // pollLast 榨取最后一个元素
        System.out.println(linkedList.pollLast());
        pringLinkedList(linkedList);
    }
}

/**运行结果
当前元素的集合：
        张三 李四 王五 李四 赵六
        1
        当前元素的集合：
        张三 李四 王五 李四 赵六
        张三
        当前元素的集合：
        张三 李四 王五 李四 赵六
        赵六
        当前元素的集合：
        张三 李四 王五 李四 赵六
        张三
        当前元素的集合：
        李四 王五 李四 赵六
        赵六
        当前元素的集合：
        李四 王五 李四 */
