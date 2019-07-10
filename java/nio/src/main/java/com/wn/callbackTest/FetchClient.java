package com.wn.callbackTest;

/**
 * Created by wangning on 2017/11/9.
 * 简要声明，把数据的回调方和处理方简称为客户和服务端
 * 1.需要有一个声明了回调函数的接口
 * 2.服务端把回调接口作为参数，把需要传递的数据通过回调函数进行包装
 * 3.客户端在需要的地方对对调函数接口实现进行引用。
 *
 */
public class FetchClient implements DataCallback {
    @Override
    public void transferData(String data) {
        System.out.println("接收到服务端数据：" + data);
    }



    public static void main(String[] args) {
        FetchClient fc = new FetchClient();
        WorkServer workServer = new WorkServer();
        workServer.dealWork(fc);
    }

}
