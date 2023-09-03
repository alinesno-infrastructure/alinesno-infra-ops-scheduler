package com.alinesno.infra.ops.scheduler;

import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;
import java.util.Map;

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
     * @param contextMap
     */
    protected abstract void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap);

    /**
     * 创建的 JdbcTemplate。
     *
     * @param jdbcUrl     JDBC URL
     * @param driverClass JDBC驱动类名
     * @param username    用户名
     * @param password    密码
     * @return JdbcTemplate对象
     */
    protected JdbcTemplate createJdbcTemplate(String jdbcUrl, String driverClass, String username, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return new JdbcTemplate(dataSource);
    }

    /**
     * 执行查询。
     *
     * @param jdbcTemplate JdbcTemplate对象
     * @param querySql     查询SQL语句
     * @return 查询结果列表
     * @throws RuntimeException 如果执行查询失败
     */
    protected List<Map<String, Object>> executeQuery(JdbcTemplate jdbcTemplate, String querySql) {
        try {
            return jdbcTemplate.queryForList(querySql);
        } catch (Exception e) {
            throw new RuntimeException("执行 MySQL 查询失败", e);
        }
    }

}
