package com.web.TaskTest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangning on 2017/1/10.
 */
public class ScheduledExecutorTest implements Runnable {

  private String jobName = "";

  public ScheduledExecutorTest(String jobName){
    this.jobName = jobName;
  }

  public void run() {
    System.out.println("ScheduledExecutor Test() :"+jobName);
  }


  public static void main(String[] args ){
    ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
    long delay = 1;
    long proid = 10;
    service.schedule(new ScheduledExecutorTest("test1"), delay, TimeUnit.SECONDS);

    service.scheduleAtFixedRate(new ScheduledExecutorTest("job1"),delay,proid,TimeUnit.SECONDS);
  }

}
