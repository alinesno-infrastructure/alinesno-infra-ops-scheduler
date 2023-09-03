package com.alinesno.infra.ops.scheduler.utils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpATTRS;
import org.junit.jupiter.api.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class SftpClientTest {
    // 测试用的主机名、用户名和密码
    private static final String HOST = "hostname";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    // 测试用的本地文件和远程文件路径
    private static final String LOCAL_FILE_PATH = "path/to/local/file.txt";
    private static final String REMOTE_FILE_PATH = "/path/to/remote/file.txt";

    // SftpClient 实例
    private SftpClient sftpClient;

    @BeforeEach
    public void setup() {
        // 在每个测试方法运行之前创建 SftpClient 实例
        sftpClient = new SftpClient(HOST, USERNAME, PASSWORD);
    }

    @AfterEach
    public void cleanup() {
        // 在每个测试方法运行之后断开连接
        sftpClient.disconnect();
    }

    @Test
    @DisplayName("测试连接到 SFTP 服务器")
    public void testConnect() throws JSchException {
        // 调用连接方法
        sftpClient.connect();

        // 断言连接是否成功
        Assertions.assertTrue(sftpClient.getSession().isConnected());
        Assertions.assertTrue(sftpClient.getChannelSftp().isConnected());
    }

    @Test
    @DisplayName("测试上传文件到 SFTP 服务器")
    public void testUploadFile() throws Exception {
        // 创建一个临时文件，并写入一些内容
        File tempFile = createTempFileWithContent("Hello, SFTP!");

        // 连接到 SFTP 服务器
        sftpClient.connect();

        // 上传文件
        sftpClient.uploadFile(tempFile.getAbsolutePath(), REMOTE_FILE_PATH);

        // 断言文件是否成功上传
        ChannelSftp channelSftp = sftpClient.getChannelSftp();
        SftpATTRS remoteFileAttrs = channelSftp.stat(REMOTE_FILE_PATH);
        Assertions.assertNotNull(remoteFileAttrs);
        Assertions.assertEquals(tempFile.length(), remoteFileAttrs.getSize());

        // 清理临时文件
        tempFile.delete();
    }

    @Test
    @DisplayName("测试断开与 SFTP 服务器的连接")
    public void testDisconnect() throws JSchException {
        // 连接到 SFTP 服务器
        sftpClient.connect();

        // 断开连接
        sftpClient.disconnect();

        // 断言连接是否已断开
        Assertions.assertFalse(sftpClient.getSession().isConnected());
        Assertions.assertFalse(sftpClient.getChannelSftp().isConnected());
    }

    // 创建一个包含指定内容的临时文件
    private File createTempFileWithContent(String content) throws Exception {
        Path tempFilePath = Files.createTempFile("test", ".txt");
        Files.write(tempFilePath, content.getBytes(), StandardOpenOption.WRITE);
        return tempFilePath.toFile();
    }
}
