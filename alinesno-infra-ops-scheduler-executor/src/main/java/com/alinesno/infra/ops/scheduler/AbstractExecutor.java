package com.alinesno.infra.ops.scheduler;

import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;

import java.util.Map;

/**
 * 任务执行器抽象类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public abstract class AbstractExecutor {


    private static final String PROP_JDBC_URL = "jdbcUrl" ;
    private static final String PROP_DRIVER_CLASS = "driverClass" ;
    private static final String PROP_USERNAME = "username" ;
    private static final String PROP_PASSWORD = "password" ;
    private static final String PROP_QUERY_SQL = "querySql" ;

    /**
     * 执行任务
     *
     * @param executorScriptDto 执行器脚本数据传输对象
     * @param contextMap
     */
    protected abstract void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap);

}
