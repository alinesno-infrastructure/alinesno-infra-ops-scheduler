package com.alinesno.infra.ops.scheduler;

import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;

/**
 * 任务执行器抽象类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public abstract class AbstractExecutor {

    /**
     * 执行任务
     *
     * @param executorScriptDto 执行器脚本数据传输对象
     */
    protected abstract void run(ExecutorScriptDto executorScriptDto);

}
