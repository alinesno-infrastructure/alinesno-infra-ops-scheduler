package com.alinesno.infra.ops.scheduler.utils;

import com.alinesno.infra.ops.scheduler.exception.ExecutorServiceRuntimeException;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FtpClientUtilTest {
    @Mock
    private FTPClient ftpClientMock;

    private FtpClientUtil ftpClientUtil;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ftpClientUtil = new FtpClientUtil("localhost", 21, "username", "password");
    }

    @Test
    public void testUpload() throws Exception {
        // 模拟FTPClient的方法
        doNothing().when(ftpClientMock).connect(anyString(), anyInt());
        when(ftpClientMock.login(anyString(), anyString())).thenReturn(true);
        doNothing().when(ftpClientMock).enterLocalPassiveMode();
        doNothing().when(ftpClientMock).setFileType(anyInt());
        doNothing().when(ftpClientMock).storeFile(anyString(), any(InputStream.class));
        doNothing().when(ftpClientMock).noop();
        doNothing().when(ftpClientMock).logout();
        doNothing().when(ftpClientMock).disconnect();

        // 执行测试
        ftpClientUtil.upload("remoteFileName", "localFileName");

        // 验证FTPClient的方法是否正确调用
        verify(ftpClientMock).connect(anyString(), anyInt());
        verify(ftpClientMock).getReplyCode();
        verify(ftpClientMock).login(anyString(), anyString());
        verify(ftpClientMock).enterLocalPassiveMode();
        verify(ftpClientMock).setFileType(anyInt());
        verify(ftpClientMock).storeFile(anyString(), any(InputStream.class));
        verify(ftpClientMock).noop();
        verify(ftpClientMock).logout();
        verify(ftpClientMock).disconnect();
    }

    @Test
    public void testUpload_ThrowsException() throws Exception {
        // 模拟FTPClient的方法抛出异常
        doThrow(new Exception("Connection error")).when(ftpClientMock).connect(anyString(), anyInt());

        // 执行测试并验证是否抛出了期望的异常
        assertThrows(ExecutorServiceRuntimeException.class, () -> {
            ftpClientUtil.upload("remoteFileName", "localFileName");
        });

        // 验证FTPClient的方法是否正确调用
        verify(ftpClientMock).connect(anyString(), anyInt());
        verifyNoMoreInteractions(ftpClientMock);
    }

    @Test
    public void testDownload() throws Exception {
        // 模拟FTPClient的方法
        doNothing().when(ftpClientMock).connect(anyString(), anyInt());
        when(ftpClientMock.login(anyString(), anyString())).thenReturn(true);
        doNothing().when(ftpClientMock).enterLocalPassiveMode();
        doNothing().when(ftpClientMock).setFileType(anyInt());
        doNothing().when(ftpClientMock).retrieveFile(anyString(), any(OutputStream.class));
        doNothing().when(ftpClientMock).noop();
        doNothing().when(ftpClientMock).logout();
        doNothing().when(ftpClientMock).disconnect();

        // 执行测试
        ftpClientUtil.download("remoteFileName", "localFileName");

        // 验证FTPClient的方法是否正确调用
        verify(ftpClientMock).connect(anyString(), anyInt());
        verify(ftpClientMock).getReplyCode();
        verify(ftpClientMock).login(anyString(), anyString());
        verify(ftpClientMock).enterLocalPassiveMode();
        verify(ftpClientMock).setFileType(anyInt());
        verify(ftpClientMock).retrieveFile(anyString(), any(OutputStream.class));
        verify(ftpClientMock).noop();
        verify(ftpClientMock).logout();
        verify(ftpClientMock).disconnect();
    }

    @Test
    public void testMkdir() throws Exception {
        // 模拟FTPClient的方法
        doNothing().when(ftpClientMock).connect(anyString(), anyInt());
        when(ftpClientMock.login(anyString(), anyString())).thenReturn(true);
        doNothing().when(ftpClientMock).enterLocalPassiveMode();
        doNothing().when(ftpClientMock).setFileType(anyInt());
        doNothing().when(ftpClientMock).makeDirectory(anyString());
        doNothing().when(ftpClientMock).noop();
        doNothing().when(ftpClientMock).logout();
        doNothing().when(ftpClientMock).disconnect();

        // 执行测试
        ftpClientUtil.mkdir("remotePathName");

        // 验证FTPClient的方法是否正确调用
        verify(ftpClientMock).connect(anyString(), anyInt());
        verify(ftpClientMock).getReplyCode();
        verify(ftpClientMock).login(anyString(), anyString());
        verify(ftpClientMock).enterLocalPassiveMode();
        verify(ftpClientMock).setFileType(anyInt());
        verify(ftpClientMock).makeDirectory(anyString());
        verify(ftpClientMock).noop();
        verify(ftpClientMock).logout();
        verify(ftpClientMock).disconnect();
    }
}
