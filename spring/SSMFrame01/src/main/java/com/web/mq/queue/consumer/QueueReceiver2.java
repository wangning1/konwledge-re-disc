package com.web.mq.queue.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by wangning on 2017/1/9.
 */
public class QueueReceiver2 implements MessageListener {
  public void onMessage(Message message) {
    try {
      System.out.println("QueueReceiver2收到的消息是："+((TextMessage)message).getText());
    } catch (JMSException e) {
      e.printStackTrace();
    }

  }
}
