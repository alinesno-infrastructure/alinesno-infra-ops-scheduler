package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.AbstractExecutor;
import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * ClickhousePlugin类是一个Clickhouse插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行Clickhouse相关任务。
 */
public class ClickhousePlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(ClickhousePlugin.class) ;

    private static final String PROP_JDBC_URL = "jdbcUrl" ;
    private static final String PROP_DRIVER_CLASS = "driverClass" ;
    private static final String PROP_USERNAME = "username" ;
    private static final String PROP_PASSWORD = "password" ;
    private static final String PROP_QUERY_SQL = "querySql" ;

    @Override
    protected void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {
        // 在此方法中实现Clickhouse相关任务的具体逻辑
        log.debug("--->>>>>>. 运行clickhouse:{}" , executorScriptDto);
    }
}
