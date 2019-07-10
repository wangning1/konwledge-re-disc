package com.web.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 业务相关调度简要说明
 * 字段         允许值               允许特殊符号
 * 秒          0-59                ，- * /
 * 分          0-59                ，- * /
 * 小时         0-23                ，- * /
 * 日期         1-31                ，- * ? / L W C
 * 月份         1-12或者JAN-DEC     ，- * /
 * 星期         1-7或者SUN-SAT      ，- * ? / L C #
 * 年（可选）     留空，1970-2099     ，- * /
 * <p>
 * *  字符代表所有可能的值
 * /  字符用来指定数值的增量
 * L  字符仅被用于天（月）和天（星期）两个子表达式，表示一个月的最后一天或者一个星期的最后一天
 * 6L 可以表示倒数第６天
 * <p>
 * Created by wangning on 2017/3/8.
 */
@Component
public class TestQuartz {

    private final static Logger logger = LoggerFactory.getLogger(TestQuartz.class);

    @Scheduled(cron = "0 0/1 11-12 * * ? ")
    public void testPerMinDruingTime() {
        logger.info("--testPerMinDruingTime[] 每天11-12点，每隔一分钟执行一次操作" + new Date(System.currentTimeMillis()));
    }

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void testPerMinQuartz() {
        logger.info("--testPerMinQuartz[] 每隔5分钟进行执行操作" + new Date(System.currentTimeMillis()));
    }

}
