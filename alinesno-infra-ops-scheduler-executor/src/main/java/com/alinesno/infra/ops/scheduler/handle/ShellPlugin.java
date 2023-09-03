package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.AbstractExecutor;
import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.scheduler.exception.ExecutorServiceRuntimeException;
import com.alinesno.infra.ops.scheduler.utils.AttributeUtils;
import com.alinesno.infra.ops.scheduler.utils.SftpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * ShellPlugin类是一个Shell插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行Shell脚本任务。
 */
public class ShellPlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(ShellPlugin.class);

    private static final String PROP_HOST = "host";
    private static final String PROP_USERNAME = "username";
    private static final String PROP_PASSWORD = "password";

    /**
     * 在此方法中实现SFTP相关任务的具体逻辑。
     *
     * @param executorScriptDto SFTP插件执行器的脚本DTO
     * @param contextMap        上下文参数Map，用于传递执行所需的上下文信息
     */
    @Override
    public void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {
        // 获取配置属性
        Map<String , Object> attrs = AttributeUtils.convertAttributesToMap(executorScriptDto.getAttributes()) ;

        String host = (String) attrs.get(PROP_HOST);
        String username = (String) attrs.get(PROP_USERNAME);
        String password = (String) attrs.get(PROP_PASSWORD);

        // 执行 SFTP 相关任务的具体逻辑
        try {
            // 创建 SFTP 连接并进行操作
            SftpClient sftpClient = new SftpClient(host, username, password);
            sftpClient.connect();

            String command = executorScriptDto.getScriptContent() ;
            String result = sftpClient.executeShellCommand(command) ;

            log.debug("result = {}" , result);

            // 关闭 SFTP 连接
            sftpClient.disconnect();
        } catch (Exception e) {
            log.error("SFTP任务执行失败: {}", e.toString());
            throw new ExecutorServiceRuntimeException(e.getMessage()) ;
        }
    }
}
