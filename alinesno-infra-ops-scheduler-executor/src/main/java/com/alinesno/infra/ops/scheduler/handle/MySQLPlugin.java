package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.AbstractExecutor;
import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;

/**
 * MySQLPlugin类是一个MySQL插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行MySQL相关任务。
 */
public class MySQLPlugin extends AbstractExecutor {

    @Override
    protected void run(ExecutorScriptDto executorScriptDto) {
        // 在此方法中实现MySQL相关任务的具体逻辑
        // ...
    }
}
