package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.AbstractExecutor;
import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import groovy.lang.GroovyShell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * GroovyPlugin类是一个Groovy插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行Groovy相关任务。
 */
public class GroovyPlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(GroovyPlugin.class);

    @Override
    protected void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {
        // 获取执行器的脚本内容
        String script = executorScriptDto.getScriptContent();

        // 获取上下文中的参数
        Object param1 = contextMap.get("param1");
        Object param2 = contextMap.get("param2");

        // 执行Groovy脚本逻辑
        try {
            GroovyShell shell = new GroovyShell();

            // 设置脚本中可用的参数
            shell.setVariable("param1", param1);
            shell.setVariable("param2", param2);

            // 执行脚本
            Object result = shell.evaluate(script);

            // 输出结果日志
            log.info("Groovy script executed successfully. Result: {}", result);
        } catch (Exception e) {
            // 处理异常情况
            log.error("Error executing Groovy script: {}", e.getMessage(), e);
        }
    }
}
