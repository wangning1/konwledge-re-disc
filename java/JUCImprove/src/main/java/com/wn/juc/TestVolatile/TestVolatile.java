package com.wn.juc.TestVolatile;

/**
 * Created by wangning on 2017/3/16.
 * 现象： 本程序有两个线程在执行，main主线程和thread线程，在执行的过程中都会有自己的栈来进行相应的信息存储，
 *        当flag没有被volatile进行修饰时，thread线程对flag的修改对主线程是不可见的，主线程不会满足条件结束程序。
 *        当使用volatile修饰flag时，flag的修改对多个线程的修改是可见的。
 *
 * 结论： volatile关键字：当多个线程进行共享数据时，可以保证内存的可见性，相较于synchronized 是一个较为轻量级的策略
 * 注意： 1.volatile不具备互斥性
 *       2.不能保证变量的原子性
 */
public class TestVolatile {
    public static void main(String[] args){
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();

        while (true){
            if(td.isFlag()){
                System.out.println("flag has been changeg . la la la ~~~");
                break;
            }
        }
    }
}


class ThreadDemo implements Runnable {

    private volatile boolean flag = false;

    public void run() {

        try {
            Thread.sleep(2000);
            flag = true;
        } catch (InterruptedException e) {
        }

        System.out.println("--flag:" + flag);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}