package com.alinesno.infra.ops.scheduler;

import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.scheduler.dto.TaskInfoDto;
import com.alinesno.infra.ops.scheduler.entity.JobEntity;
import com.alinesno.infra.ops.scheduler.service.IExecutorScriptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * ExecutorScriptServiceImpl类是运行脚本的实现类，实现了IExecutorScriptService接口。
 * 它用于运行脚本任务。
 */
@Service
public class ExecutorScriptServiceImpl implements IExecutorScriptService {

    private static final Logger log = LoggerFactory.getLogger(ExecutorScriptServiceImpl.class);

    @Override
    public void run(ExecutorScriptDto executorScriptDto) {
        // 获取任务类型
        String type = executorScriptDto.getType();

        log.debug("运行脚本服务: {}", executorScriptDto);
        // 确保任务类型不为空
        Assert.hasLength(type, "任务类型为空.");

        // 根据任务类型获取相应的插件并执行脚本
        ScriptExecutorContext.getRunnerPlugin(type).run(executorScriptDto);
    }

    /**
     * 任务信息实体
     * @param executorScriptDto 执行器脚本DTO
     * @return
     */
    @Override
    public JobEntity analyzeTaskInfo(TaskInfoDto executorScriptDto) {
        // 在此方法中实现分析任务信息的逻辑



        return null;
    }
}
