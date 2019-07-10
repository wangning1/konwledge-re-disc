package com.wn.callbackTest;

/**
 * Created by wangning on 2017/11/9.
 */
public class WorkServer {

    public void dealWork(DataCallback dataCallback){
        String msg = "服务端处理好了数据，请查收！";
        dataCallback.transferData(msg);
    }

}
