package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.AbstractExecutor;
import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;

/**
 * SftpPlugin类是一个SFTP插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行SFTP相关任务。
 */
public class SftpPlugin extends AbstractExecutor {

    @Override
    protected void run(ExecutorScriptDto executorScriptDto) {
        // 在此方法中实现SFTP相关任务的具体逻辑
        // ...
    }
}
