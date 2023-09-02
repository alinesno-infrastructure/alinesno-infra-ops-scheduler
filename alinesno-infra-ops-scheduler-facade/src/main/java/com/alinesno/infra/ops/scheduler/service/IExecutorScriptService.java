package com.alinesno.infra.ops.scheduler.service;

import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.scheduler.dto.TaskInfoDto;
import com.alinesno.infra.ops.scheduler.entity.JobEntity;

/**
 * IExecutorScriptService接口是运行脚本的服务接口。
 * 它定义了运行脚本和分析任务信息的方法。
 */
public interface IExecutorScriptService {

    /**
     * 运行脚本
     *
     * @param executorScriptDto 执行器脚本DTO
     */
    void run(ExecutorScriptDto executorScriptDto);

    /**
     * 分析任务信息
     *
     * @param executorScriptDto 执行器脚本DTO
     * @return 任务实体对象
     */
    JobEntity analyzeTaskInfo(TaskInfoDto executorScriptDto);

}
