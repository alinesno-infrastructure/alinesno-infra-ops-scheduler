package com.alinesno.infra.ops.scheduler.utils;

import com.jcraft.jsch.*;

public class SftpClient {
    private String host;
    private String username;
    private String password;
    private Session session;
    private ChannelSftp channelSftp;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public ChannelSftp getChannelSftp() {
        return channelSftp;
    }

    public void setChannelSftp(ChannelSftp channelSftp) {
        this.channelSftp = channelSftp;
    }

    public SftpClient(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public void connect() throws JSchException {
        JSch jsch = new JSch();
        session = jsch.getSession(username, host);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        Channel channel = session.openChannel("sftp");
        channel.connect();
        channelSftp = (ChannelSftp) channel;
    }

    public void uploadFile(String localFilePath, String remoteFilePath) throws SftpException {
        channelSftp.put(localFilePath, remoteFilePath);
    }

    public void disconnect() {
        if (channelSftp != null) {
            channelSftp.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }
}
