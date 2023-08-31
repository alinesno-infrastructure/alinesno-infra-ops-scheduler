package com.alinesno.infra.ops.scheduler.quartz.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class JobAlarmEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(JobAlarmEvent e) {
        applicationEventPublisher.publishEvent(e) ;
    }
}