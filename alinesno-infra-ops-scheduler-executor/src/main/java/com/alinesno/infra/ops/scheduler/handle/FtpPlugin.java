package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.AbstractExecutor;
import com.alinesno.infra.ops.scheduler.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.scheduler.utils.AttributeUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * FtpPlugin类是一个FTP插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行FTP相关任务，包括文件上传等操作。
 */
public class FtpPlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(FtpPlugin.class);

    protected static final String PROP_HOST = "host";
    protected static final String PROP_USERNAME = "username";
    protected static final String PROP_PASSWORD = "password";
    protected static final String PROP_PATH = "path";

    /**
     * 重写父类的run方法，执行FTP文件上传任务。
     *
     * @param executorScriptDto 执行器脚本DTO对象
     * @param contextMap        上下文参数映射表，包含FTP连接所需的参数
     */
    @Override
    protected void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {

        Map<String , Object> attrs = AttributeUtils.convertAttributesToMap(executorScriptDto.getAttributes()) ;

        String host = (String) attrs.get(PROP_HOST);
        String username = (String) attrs.get(PROP_USERNAME);
        String password = (String) attrs.get(PROP_PASSWORD);
        String path = (String) attrs.get(PROP_PATH);

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(host);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            File file = new File(path);
            FileInputStream inputStream = new FileInputStream(file);
            String remoteFileName = file.getName();
            boolean uploaded = ftpClient.storeFile(remoteFileName, inputStream);
            inputStream.close();

            if (uploaded) {
                log.info("File uploaded successfully.");
            } else {
                log.error("Failed to upload file.");
            }
        } catch (IOException e) {
            log.error("FTP error: " + e.getMessage(), e);
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                log.error("FTP error: " + e.getMessage(), e);
            }
        }
    }
}
