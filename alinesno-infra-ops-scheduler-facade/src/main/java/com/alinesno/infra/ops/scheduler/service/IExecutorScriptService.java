package com.alinesno.infra.ops.scheduler.service;

import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;

/**
 * 运行脚本服务
 */
public interface IExecutorScriptService {

    /**
     * 运行脚本
     * @param executorScriptDto
     */
    public void run(ExecutorScriptDto executorScriptDto)  ;

}
