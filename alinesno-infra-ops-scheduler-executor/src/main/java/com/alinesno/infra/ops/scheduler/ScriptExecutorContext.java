package com.alinesno.infra.ops.scheduler;

import cn.hutool.extra.ftp.Ftp;
import com.alinesno.infra.ops.scheduler.handle.*;

import java.util.HashMap;
import java.util.Map;

/**
 * ScriptExecutorContext类是一个脚本容器类，用于管理不同类型的脚本执行器。
 */
public class ScriptExecutorContext {

    // 使用Map来存储不同类型的脚本执行器
    private static final Map<String, AbstractExecutor> mapPlugin = new HashMap<>();

    static {
        // 将ShellPlugin注册到脚本容器中
        mapPlugin.put("shell", new ShellPlugin());
        mapPlugin.put("alert", new AlarmPlugin());
        mapPlugin.put("ansible", new AnsiblePlugin());
        mapPlugin.put("clickhouse", new ClickhousePlugin());
        mapPlugin.put("config", new ConfigPlugin());
        mapPlugin.put("ftp", new FtpPlugin());
        mapPlugin.put("sftp", new SftpPlugin());
        mapPlugin.put("groovy", new GroovyPlugin());
        mapPlugin.put("mysql", new MySQLPlugin());
    }

    /**
     * 根据脚本插件名称获取相应的脚本执行器
     * @param pluginName 脚本插件名称
     * @return 对应的脚本执行器
     */
    public AbstractExecutor getRunnerPlugin(String pluginName) {
        return mapPlugin.get(pluginName);
    }
}
