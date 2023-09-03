package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.scheduler.utils.AttributeUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("GitPlugin 单元测试")
public class GitPluginTest {

    @Mock
    private Git gitMock;

    @Mock
    private ExecutorScriptDto executorScriptDtoMock;

    @Test
    @DisplayName("测试 run 方法")
    public void testRun() throws Exception {
        // 初始化 Mockito 注解
        MockitoAnnotations.openMocks(this);

        // 创建 GitPlugin 实例
        GitPlugin gitPlugin = new GitPlugin();

        // 准备测试数据
        String repositoryUrl = "https://github.com/example/repository.git";
        String localPath = "/path/to/local/repository";
        Map<String, Object> attributes = getStringObjectMap(repositoryUrl, localPath);
        when(executorScriptDtoMock.getAttributes()).thenReturn(AttributeUtils.convertMapToAttributes(attributes));

        // 设置 GitPlugin 的克隆方法返回 gitMock
        when(gitPlugin.cloneRepository(anyString(), anyString(), any(CredentialsProvider.class))).thenReturn(gitMock);

        // 设置 GitPlugin 的其他方法返回空
        doNothing().when(gitPlugin).createBranch(any(Git.class), anyString());
        doNothing().when(gitPlugin).createTags(any(Git.class), anyString());
        doNothing().when(gitPlugin).uploadChanges(any(Git.class), any(CredentialsProvider.class));

        // 创建上下文 Map
        Map<String, Object> contextMap = new HashMap<>();

        // 调用 run 方法
        gitPlugin.run(executorScriptDtoMock, contextMap);

        // 验证克隆方法被调用一次
        verify(gitPlugin, times(1)).cloneRepository(repositoryUrl, localPath, any(CredentialsProvider.class));

        // 验证其他方法被调用一次
        verify(gitPlugin, times(1)).createBranch(gitMock, "new_branch");
        verify(gitPlugin, times(1)).createTags(gitMock, "v1.0");
        verify(gitPlugin, times(1)).uploadChanges(gitMock, any(CredentialsProvider.class));

        // 验证上下文 Map 中的状态和错误信息
        assertEquals("success", contextMap.get("gitStatus"));
        assertNull(contextMap.get("errorMessage"));

        // 验证 gitMock 被关闭一次
        verify(gitMock, times(1)).close();
    }

    private static Map<String, Object> getStringObjectMap(String repositoryUrl, String localPath) {
        String username = "username";
        String password = "password";
        String token = "token";

        // 设置 executorScriptDtoMock 的属性
        Map<String, Object> attributes = new HashMap<>();
        attributes.put(GitPlugin.PROP_REPOSITORY_URL, repositoryUrl);
        attributes.put(GitPlugin.PROP_LOCAL_PATH, localPath);
        attributes.put(GitPlugin.PROP_USERNAME, username);
        attributes.put(GitPlugin.PROP_PASSWORD, password);
        attributes.put(GitPlugin.PROP_GITHUB_TOKEN, token);
        return attributes;
    }
}
