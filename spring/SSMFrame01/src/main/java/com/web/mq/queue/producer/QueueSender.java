package com.web.mq.queue.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class QueueSender {

  private final static Logger logger = LoggerFactory.getLogger(QueueSender.class);

  @Autowired
  @Qualifier("jmsQueueTemplate")
  private JmsTemplate jmsTemplate;

  public void sendMessage(String queueName, final String message){
    jmsTemplate.send(queueName, new MessageCreator() {
      public Message createMessage(Session session) throws JMSException {
        return session.createTextMessage(message);
      }
    });
  }




}
