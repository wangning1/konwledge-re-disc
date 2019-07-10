package com.web.controller;

import com.web.mq.queue.producer.QueueSender;
import com.web.mq.topic.producer.TopicSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangning on 2017/1/9.
 */
@Controller
@RequestMapping(value = "/mq")
public class ActivemqController {

  private final static Logger logger = LoggerFactory.getLogger(ActivemqController.class);

  @Autowired
  private QueueSender queueSender;

  @Autowired
  private TopicSender topicSender;

  @RequestMapping(value= "/testQueue")
  @ResponseBody
  public String testQueue(){
    logger.info("-- 已发送信息到queue --");
    queueSender.sendMessage("test.queue","this is jms'test for  Queue");
    return "this is jms'test for Queue";
  }

  @RequestMapping(value = "/testTopic")
  @ResponseBody
  public String testTopic(){
    logger.info("--已发送信息到topic--");
    topicSender.sendMessage("test.topic","test for Topic");
    return "this is jms'test for topic";
  }


}
