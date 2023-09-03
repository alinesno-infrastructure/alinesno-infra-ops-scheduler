package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.command.domain.CmdResult;
import com.alinesno.infra.ops.scheduler.command.runner.CmdExecutor;
import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class ShellPluginTest {

    @Mock
    private ExecutorScriptDto executorScriptDto;

    @BeforeEach
    void setUp() {
        // 初始化 Mockito 注解
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void run_ShouldExecuteShellScriptAndPrintResult_WhenDtoIsValid() {
        // 准备
        ShellPlugin shellPlugin = new ShellPlugin();
        Map<String, Object> contextMap = new HashMap<>();
        when(executorScriptDto.getScriptContent()).thenReturn("echo 'Hello, World!'");

        // 设置模拟的CmdResult
        CmdResult cmdResult = createMockCmdResult();
        CmdExecutor cmdExecutor = mock(CmdExecutor.class);
        when(cmdExecutor.run()).thenReturn(cmdResult);
//        doReturn(cmdExecutor).when(shellPlugin).createCmdExecutor(any(), any(), any(), any(), any(), any(), any());

        // 执行
        shellPlugin.run(executorScriptDto, contextMap);

        // 断言
        // 验证 createCmdExecutor 方法是否被调用
//        verify(shellPlugin).createCmdExecutor(any(), any(), any(), any(), any(), any(), any());
        // 验证 CmdExecutor 的 run 方法是否被调用
        verify(cmdExecutor).run();
        // 验证结果是否正确输出
//        assertTrue(cmdResult.getOutput().contains("Hello, World!"));
    }

    private CmdResult createMockCmdResult() {
        // 创建模拟的 CmdResult
        CmdResult cmdResult = new CmdResult();
//        cmdResult.setOutput("Hello, World!");
        return cmdResult;
    }
}
