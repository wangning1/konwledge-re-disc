package com.web.TaskTest;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wangning on 2017/1/10.
 */
public class TimerTest extends TimerTask {

  private String jobName ="";

  public TimerTest(){}

  public TimerTest(String jobName){
    super();
    this.jobName = jobName;
  }

  public void run() {
   System.out.println("execute() " + jobName);
  }

  public static void main(String[] args){
    long delay = 1000;
    long proid = 10;
    Timer timer = new Timer();
    timer.schedule(new TimerTest("test1"),delay);
    timer.schedule(new TimerTest("test2"),delay,proid);
  }
}
