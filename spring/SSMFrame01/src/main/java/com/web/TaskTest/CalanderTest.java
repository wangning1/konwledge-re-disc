package com.web.TaskTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wangning on 2017/1/10.
 */
public class CalanderTest {

  public static void main(String[] args){

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date(System.currentTimeMillis()));
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    System.out.println(format.format(new Date(calendar.getTime().getTime())));

    System.out.println();

    System.out.println(calendar.YEAR);
    System.out.println(calendar.MONTH);
    System.out.println(calendar.DAY_OF_WEEK_IN_MONTH);
    System.out.println(calendar.DAY_OF_WEEK);
  }

}
