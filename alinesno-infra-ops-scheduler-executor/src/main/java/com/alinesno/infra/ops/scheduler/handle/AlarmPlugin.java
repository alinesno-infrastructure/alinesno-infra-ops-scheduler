package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.AbstractExecutor;
import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * AlarmPlugin类是一个告警插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行告警相关任务。
 */
public class AlarmPlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(AlarmPlugin.class) ;

    private static final String PROP_TYPE = "type" ;  //ding-talk/wechat/http
    private static final String PROP_WEBHOOK = "webhook" ;
    private static final String PROP_SECRET = "secret" ;

    @Override
    protected void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {
        // 在此方法中实现告警相关任务的具体逻辑
        // ...
    }
}
