package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.AbstractExecutor;
import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;

/**
 * ConfigPlugin类是一个配置插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行配置任务。
 */
public class ConfigPlugin extends AbstractExecutor {

    @Override
    protected void run(ExecutorScriptDto executorScriptDto) {
        // 在此方法中实现配置任务的具体逻辑
        // ...
    }
}
