package com.alinesno.infra.ops.scheduler.quartz.event;

import com.alinesno.infra.ops.scheduler.entity.TransEntity;
import com.alinesno.infra.ops.scheduler.service.ITransService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class JobAlarmEventSubscriber implements ApplicationListener<JobAlarmEvent> {

    private static final Logger log = LoggerFactory.getLogger(JobAlarmEventSubscriber.class) ;

    @Autowired
    private ITransService transService ;

    @Override
    public void onApplicationEvent(JobAlarmEvent event) {
        log.info("trans count = {}" , event);

        TransEntity trans = transService.getById(event.getTransId()) ;
        trans.setTotalDataCount(event.getTotalCount());
        trans.setProcessDataCount(event.getTransCount());

        transService.update(trans) ;
    }
}