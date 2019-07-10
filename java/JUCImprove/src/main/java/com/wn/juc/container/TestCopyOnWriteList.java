package com.wn.juc.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by wangning on 2017/3/16.
 * 现象：ArrayList和Collections包装的ArrayList并发处理多线程边遍历边写时会 ConcurrentModificationException
 *      使用CopyOnWriteArrayList 可以是多线程并发处理更安全
 *
 * 结论：CopyOnWriteArrayList/CopyOnWriteArraySet : “写入并复制”
 *       注意：添加操作多时，效率低，因为每次添加时都会进行复制，开销非常的大。并发迭代操作多时可以选择。
 *
 *
 * 类似的：
 * 此包还提供了设计用于多线程上下文中的Collection 实现：ConcurrentHashMap、ConcurrentSkipListMap、ConcurrentSkipListSet、
 * CopyOnWriteArrayList 和CopyOnWriteArraySet。当期望许多线程访问一个给定collection 时，ConcurrentHashMap 通常优于同步的HashMap，
 * ConcurrentSkipListMap 通常优于同步的TreeMap。当期望的读数和遍历远远大于列表的更新数时，CopyOnWriteArrayList 优于同步的ArrayList
 *
 */
public class TestCopyOnWriteList {

    public static void main(String[] args) {
//        ArrayListDemo ad = new ArrayListDemo();
        CopyOnWriteArrayListDemo ad = new CopyOnWriteArrayListDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(ad).start();
        }
    }

}

class CopyOnWriteArrayListDemo implements Runnable{

    private static CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<String>();
    static {
        arrayList.add("a1");
        arrayList.add("a2");
        arrayList.add("a3");
    }

    public void run() {
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
            arrayList.add("lala@@");
        }
    }
}

class ArrayListDemo implements Runnable {

    private static List<String> list = Collections.synchronizedList(new ArrayList<String>());
//    private static ArrayList<String> list = new ArrayList<String>();

    static {
        list.add("A1");
        list.add("A2");
        list.add("A3");
    }

    public void run() {

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            list.add("haha~~");
        }
    }
}
