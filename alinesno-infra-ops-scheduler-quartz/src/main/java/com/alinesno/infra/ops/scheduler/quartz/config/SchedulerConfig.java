package com.alinesno.infra.ops.scheduler.quartz.config;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.concurrent.Executor;

@Configuration
public class SchedulerConfig {
 
   @Autowired
    private DataSource dataSource;
 
    /**
     * 调度器
     *
     * @return
     * @throws Exception
     */
    @Bean
    public Scheduler scheduler() throws Exception {
        return schedulerFactoryBean().getScheduler();
    }
 
    /**
     * Scheduler工厂类
     *
     * @return
     * @throws IOException
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
       SchedulerFactoryBean factory = new SchedulerFactoryBean();
       factory.setSchedulerName("Cluster_Scheduler");
       factory.setDataSource(dataSource);
       factory.setApplicationContextSchedulerContextKey("applicationContext");
       factory.setTaskExecutor(schedulerThreadPool());
       factory.setStartupDelay(0);// 延迟0s执行
       return factory;
    }
 
    /**
     * 配置Schedule线程池
     *
     * @return
     */
    @Bean
    public Executor schedulerThreadPool() {
       ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
       executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
       executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors());
       executor.setQueueCapacity(Runtime.getRuntime().availableProcessors());
       return executor;
    }
}