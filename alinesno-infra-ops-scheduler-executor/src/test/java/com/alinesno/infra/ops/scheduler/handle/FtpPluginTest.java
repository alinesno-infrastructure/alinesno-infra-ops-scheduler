package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.alinesno.infra.ops.scheduler.handle.FtpPlugin.*;

@DisplayName("FtpPlugin 单元测试")
class FtpPluginTest {

    @Test
    @DisplayName("测试 run 方法")
    void run_ShouldUploadFileToFTP() {
        // 准备
        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put(PROP_HOST, "ftp.example.com");
        contextMap.put(PROP_USERNAME, "your_username");
        contextMap.put(PROP_PASSWORD, "your_password");
        contextMap.put(PROP_PATH, "/path/to/local/file.txt");

        FtpPlugin ftpPlugin = new FtpPlugin();

        ExecutorScriptDto dto = new ExecutorScriptDto();

        // 执行
        ftpPlugin.run(dto, contextMap);

        // 断言
        // 在这里添加适当的断言来验证文件是否成功上传到 FTP 服务器
        // 可以使用 FTP 客户端库或模拟 FTP 服务器进行断言
        // 例如：
        // assertTrue(ftpClient.fileExists("/path/to/remote/file.txt"));
    }
}
