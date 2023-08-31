package com.alinesno.infra.ops.scheduler.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AlarmEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(AlarmEvent e) {
        applicationEventPublisher.publishEvent(e) ;
    }
}