package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.alinesno.infra.ops.scheduler.handle.FtpPlugin.*;
import static org.junit.jupiter.api.Assertions.*;

class FtpPluginTest {

    @Test
    void run() {

        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put(FtpPlugin.PROP_HOST, "ftp.example.com");
        contextMap.put(FtpPlugin.PROP_USERNAME, "your_username");
        contextMap.put(FtpPlugin.PROP_PASSWORD, "your_password");
        contextMap.put(FtpPlugin.PROP_PATH, "/path/to/local/file.txt");

        FtpPlugin ftpPlugin = new FtpPlugin();

        ExecutorScriptDto dto = new ExecutorScriptDto() ;
        ftpPlugin.run(dto, contextMap);

    }
}