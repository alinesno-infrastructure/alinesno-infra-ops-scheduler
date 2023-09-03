package com.alinesno.infra.ops.scheduler.handle;

import com.alinesno.infra.ops.scheduler.utils.SftpClient;
import com.jcraft.jsch.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SftpClient 单元测试")
class SftpClientTest {
    private static final String HOST = "example.com";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String LOCAL_FILE_PATH = "/path/to/local/file.txt";
    private static final String REMOTE_FILE_PATH = "/path/to/remote/file.txt";

    private SftpClient sftpClient;

    @BeforeEach
    void setup() {
        sftpClient = new SftpClient(HOST, USERNAME, PASSWORD);
    }

    @AfterEach
    void teardown() {
        sftpClient.disconnect();
    }

    @Test
    @DisplayName("测试连接到 SFTP 服务器")
    void testConnect() {
        assertDoesNotThrow(() -> sftpClient.connect());
        assertTrue(sftpClient.getSession().isConnected());
        assertTrue(sftpClient.getChannelSftp().isConnected());
    }

    @Test
    @DisplayName("测试上传文件到 SFTP 服务器")
    void testUploadFile() throws JSchException, SftpException, IOException {
        sftpClient.connect();

        // 创建一个临时的本地文件用于测试
        File localFile = createTemporaryFile();

        assertDoesNotThrow(() -> sftpClient.uploadFile(localFile.getAbsolutePath(), REMOTE_FILE_PATH));
        assertTrue(checkIfFileExists(REMOTE_FILE_PATH));

        // 清理测试环境，删除临时文件和远程文件
        localFile.delete();
        deleteRemoteFile(REMOTE_FILE_PATH);
    }

    private File createTemporaryFile() throws IOException {
        // 在指定路径创建一个临时文件
        File file = new File(LOCAL_FILE_PATH);
        file.createNewFile();
        return file;
    }

    private boolean checkIfFileExists(String filePath) throws SftpException {
        // 检查远程文件是否存在
        try {
            sftpClient.getChannelSftp().lstat(filePath);
            return true;
        } catch (SftpException e) {
            return false;
        }
    }

    private void deleteRemoteFile(String filePath) throws SftpException {
        // 删除远程文件
        sftpClient.getChannelSftp().rm(filePath);
    }
}
