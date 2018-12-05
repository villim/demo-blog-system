package io.github.villim.blog.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration("cache-rest-scheduler-context")
public class CacheSchedulerApplicationContext {

//    @Bean
//    MethodInvokingJobDetailFactoryBean updateSparkpostDataJob() {
//        MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
//        jobDetailFactoryBean.setTargetBeanName("sparkpostService");
//        jobDetailFactoryBean.setTargetMethod("cacheSparkPostMetrics");
//        jobDetailFactoryBean.setConcurrent(false);
//        return jobDetailFactoryBean;
//    }
//
//    @Bean
//    CronTriggerFactoryBean cronTrigger() {
//        CronTriggerFactoryBean cronTrigger = new CronTriggerFactoryBean();
//        cronTrigger.setName("Update Sparkpost available message number");
//        cronTrigger.setJobDetail(updateSparkpostDataJob().getObject());
//        cronTrigger.setCronExpression("5 * * * * ?"); //TODO: every 5 min
//        return cronTrigger;
//    }
//
//    @Bean
//    SchedulerFactoryBean schedulerFactoryBean() {
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        schedulerFactoryBean.setJobDetails(updateSparkpostDataJob().getObject());
//        schedulerFactoryBean.setTriggers(cronTrigger().getObject());
//        return schedulerFactoryBean;
//    }

}
