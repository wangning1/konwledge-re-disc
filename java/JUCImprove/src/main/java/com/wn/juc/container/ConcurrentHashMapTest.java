package com.wn.juc.container;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangning on 2017/3/16.
 * 现象 ：当使用hashMap 或者 Collections 包装成的同步容器 在边遍历边删除的情况下会报ConcurrentModificationException
 *        使用CurrentHashMap可以正常执行
 *
 * 结论：CurrentHashMap 使用分段锁机制进行多线程处理，在多线程上的处理更安全。
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
//        HashMapDemo hd = new HashMapDemo();
        ConcurrentHashMapDemo hd = new ConcurrentHashMapDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(hd).start();
        }
    }

}

class ConcurrentHashMapDemo implements Runnable {

    private static ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<Integer, String>();

    static {
        for (int i = 0; i < 15; i++) {
            concurrentHashMap.put(i, i + "");
        }
    }

    public void run() {
        for (Map.Entry<Integer, String> entry : concurrentHashMap.entrySet()) {
            if (entry.getKey() < 5) {
                System.out.println("Current thread is " + Thread.currentThread().getName() + " currentHashMap remove key:" + entry.getKey() + " " + concurrentHashMap.remove(entry.getKey()));
            }
        }
    }
}

class HashMapDemo implements Runnable {

    private static Map<Integer, String> hashMap = Collections.synchronizedMap(new HashMap<Integer, String>());
//    private static HashMap<Integer, String> hashMap = new HashMap<Integer, String>();

    static {
        for (int i = 0; i < 15; i++) {
            hashMap.put(i, "" + i);
        }
    }

    public void run() {
        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            if (entry.getKey() < 5) {
                System.out.println("remove " + entry.getKey());
                hashMap.remove(entry.getKey());
            }
        }
    }
}
