package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.AbstractExecutor;
import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;

/**
 * ClickhousePlugin类是一个Clickhouse插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行Clickhouse相关任务。
 */
public class ClickhousePlugin extends AbstractExecutor {

    @Override
    protected void run(ExecutorScriptDto executorScriptDto) {
        // 在此方法中实现Clickhouse相关任务的具体逻辑
        // ...
    }
}
