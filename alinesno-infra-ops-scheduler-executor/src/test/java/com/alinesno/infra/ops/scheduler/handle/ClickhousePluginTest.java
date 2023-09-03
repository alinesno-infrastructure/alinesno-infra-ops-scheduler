package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.scheduler.dto.ValueAttributeDto;
import com.alinesno.infra.ops.scheduler.utils.AttributeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ClickhousePluginTest {
    @Test
    void testRun() {
        // 准备测试数据
        ExecutorScriptDto executorScriptDto = new ExecutorScriptDto();

        List<ValueAttributeDto> properties = new ArrayList<>();

        properties.add(new ValueAttributeDto("jdbcUrl", "jdbc:clickhouse://localhost:8123/mydatabase"));
        properties.add(new ValueAttributeDto("driverClass", "ru.yandex.clickhouse.ClickHouseDriver"));
        properties.add(new ValueAttributeDto("username", "myusername"));
        properties.add(new ValueAttributeDto("password", "mypassword"));
        properties.add(new ValueAttributeDto("querySql", "SELECT * FROM mytable"));

        executorScriptDto.setAttributes(properties);

        ClickhousePlugin clickhousePlugin = new ClickhousePlugin();
        Map<String, Object> contextMap = new HashMap<>();

        // 运行测试
        clickhousePlugin.run(executorScriptDto, contextMap);

        // 验证结果
        List<Map<String, Object>> queryResult = (List<Map<String, Object>>) contextMap.get("clickhouseResult");
        Assertions.assertNotNull(queryResult);
        Assertions.assertFalse(queryResult.isEmpty());
        // 可以根据实际情况添加更多的验证逻辑
    }
}
