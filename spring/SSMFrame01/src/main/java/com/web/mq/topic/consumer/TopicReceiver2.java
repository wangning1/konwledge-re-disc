package com.web.mq.topic.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by wangning on 2017/1/9.
 */
public class TopicReceiver2 implements MessageListener {

  public void onMessage(Message message) {
    try {
      System.out.println("topicreceiver2 receive message:" + ((TextMessage) message).getText());
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

}