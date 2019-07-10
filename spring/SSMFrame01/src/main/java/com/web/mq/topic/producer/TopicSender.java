package com.web.mq.topic.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by wangning on 2017/1/9.
 */
public class TopicSender {

  @Autowired
  @Qualifier("jmsTopicTemplate")
  private JmsTemplate jmsTemplate;

  public void sendMessage(String topicName, final String message){
    jmsTemplate.send(topicName, new MessageCreator() {
      public Message createMessage(Session session) throws JMSException {
        return session.createTextMessage(message);
      }
    });
  }

}
