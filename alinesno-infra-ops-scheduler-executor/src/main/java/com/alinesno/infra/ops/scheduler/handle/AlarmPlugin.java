package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.AbstractExecutor;
import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;

/**
 * AlarmPlugin类是一个告警插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行告警相关任务。
 */
public class AlarmPlugin extends AbstractExecutor {

    @Override
    protected void run(ExecutorScriptDto executorScriptDto) {
        // 在此方法中实现告警相关任务的具体逻辑
        // ...
    }
}
