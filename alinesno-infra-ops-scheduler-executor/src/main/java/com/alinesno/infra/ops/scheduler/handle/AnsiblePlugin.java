package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.AbstractExecutor;
import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * AnsiblePlugin类是一个Ansible插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行Ansible相关任务。
 */
public class AnsiblePlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(AnsiblePlugin.class) ;

    @Override
    protected void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {
        // 在此方法中实现Ansible相关任务的具体逻辑
        // ...
    }
}
