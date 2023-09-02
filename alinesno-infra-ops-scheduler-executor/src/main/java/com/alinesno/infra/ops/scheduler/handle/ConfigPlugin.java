package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.AbstractExecutor;
import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * ConfigPlugin类是一个配置插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行配置任务。
 */
public class ConfigPlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(ClickhousePlugin.class) ;

    private static final String PROP_CONFIG_URL = "config-url" ;

    @Override
    protected void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {
        // 在此方法中实现配置任务的具体逻辑
        // ...
    }
}
