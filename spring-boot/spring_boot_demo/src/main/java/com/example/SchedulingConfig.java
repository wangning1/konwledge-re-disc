package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by wangning on 2017/10/24.
 */
@Configuration
@EnableScheduling //启用定时任务
public class SchedulingConfig {

    private final Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);

    @Scheduled(cron = "0/30 * * * * ?")
    public void schedulingTask(){
        logger.info("----I'll do every 30 seconds.");
    }
}
