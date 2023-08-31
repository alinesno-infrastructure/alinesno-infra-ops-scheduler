package com.alinesno.infra.ops.scheduler.api.provider;

import com.alinesno.infra.common.core.context.SpringContext;
import com.alinesno.infra.ops.scheduler.service.IDistSchedulerService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 数据转换任务
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class DataTransferJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(DataTransferJob.class) ;

    @Override
    public void execute(JobExecutionContext context) {

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap() ;

        Long jobId = jobDataMap.getLong("jobId") ;
        log.debug("jobId = {}" , jobId);

        IDistSchedulerService distSchedulerService = SpringContext.getBean(IDistSchedulerService.class) ;

        try {
            distSchedulerService.createCronJob(jobId);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

    }

}