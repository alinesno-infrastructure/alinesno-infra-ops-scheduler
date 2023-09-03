package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.AbstractExecutor;
import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.scheduler.utils.AttributeUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class ClickhousePlugin extends AbstractExecutor {

    private static final String PROP_JDBC_URL = "jdbcUrl";
    private static final String PROP_DRIVER_CLASS = "driverClass";
    private static final String PROP_USERNAME = "username";
    private static final String PROP_PASSWORD = "password";
    private static final String PROP_QUERY_SQL = "querySql";

    public void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {

        // 获取配置属性
        Map<String , Object> attrs = AttributeUtils.convertAttributesToMap(executorScriptDto.getAttributes()) ;

        String jdbcUrl = (String) attrs.get(PROP_JDBC_URL);
        String driverClass = (String) attrs.get(PROP_DRIVER_CLASS);
        String username = (String) attrs.get(PROP_USERNAME);
        String password = (String) attrs.get(PROP_PASSWORD);
        String querySql = (String) attrs.get(PROP_QUERY_SQL);

        // 创建 ClickHouse 的 JdbcTemplate
        JdbcTemplate jdbcTemplate = createJdbcTemplate(jdbcUrl, driverClass, username, password);

        // 执行 ClickHouse 的查询 SQL
        List<Map<String, Object>> queryResult = executeQuery(jdbcTemplate, querySql);

        // 将查询结果放入 contextMap
        contextMap.put("clickhouseResult", queryResult);
    }

//    // 执行 ClickHouse 的查询 SQL，并返回结果
//    private List<Map<String, Object>> executeClickHouseQuery(JdbcTemplate jdbcTemplate, String querySql) {
//        try {
//            // 使用 JdbcTemplate 执行查询 SQL
//            return jdbcTemplate.queryForList(querySql);
//        } catch (Exception e) {
//            throw new RuntimeException("执行 ClickHouse 查询失败", e);
//        }
//    }
//
//    // 创建 ClickHouse 的 JdbcTemplate
//    private JdbcTemplate createJdbcTemplate(String jdbcUrl, String driverClass, String username, String password) {
//        // 创建 JdbcTemplate 实例
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        // 配置数据源
//        jdbcTemplate.setDataSource(createDataSource(jdbcUrl, driverClass, username, password));
//        return jdbcTemplate;
//    }
//
//    // 创建 ClickHouse 的数据源
//    private DataSource createDataSource(String jdbcUrl, String driverClass, String username, String password) {
//        // 创建数据源实例
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        // 配置 JDBC URL、驱动类、用户名和密码
//        dataSource.setUrl(jdbcUrl);
//        dataSource.setDriverClassName(driverClass);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        return dataSource;
//    }

}
