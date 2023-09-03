package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.AbstractExecutor;
import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.scheduler.utils.SftpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * SftpPlugin类是一个SFTP插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行SFTP相关任务。
 */
public class SftpPlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(SftpPlugin.class);

    private static final String PROP_HOST = "host";
    private static final String PROP_USERNAME = "username";
    private static final String PROP_PASSWORD = "password";
    private static final String PROP_PATH = "path";

    /**
     * 在此方法中实现SFTP相关任务的具体逻辑。
     *
     * @param executorScriptDto SFTP插件执行器的脚本DTO
     * @param contextMap        上下文参数Map，用于传递执行所需的上下文信息
     */
    @Override
    public void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {
        // 从上下文参数中获取所需的属性值
        String host = (String) contextMap.get(PROP_HOST);
        String username = (String) contextMap.get(PROP_USERNAME);
        String password = (String) contextMap.get(PROP_PASSWORD);
        String path = (String) contextMap.get(PROP_PATH);

        // 执行 SFTP 相关任务的具体逻辑
        try {
            // 创建 SFTP 连接并进行操作
            SftpClient sftpClient = new SftpClient(host, username, password);
            sftpClient.connect();

            // 在指定路径下上传或下载文件
            // 例如，上传文件到远程服务器
            String localFilePath = null ; //  executorScriptDto.getScriptPath();
            sftpClient.uploadFile(null, path);

            // 关闭 SFTP 连接
            sftpClient.disconnect();
        } catch (Exception e) {
            log.error("SFTP任务执行失败: {}", e.getMessage());
        }
    }
}
