package com.alinesno.infra.ops.scheduler.service;

/**
 * 数据库管理服务接口
 */
public interface IDMDBService {

    /**
     * 服务器密钥分发
     *
     * <p>该方法用于批量分发 SSH 密钥到各个服务器，用于初始化服务器管理。可以通过上传包含服务器密钥的文件进行批量操作。</p>
     *
     * @param keyFilePath 密钥文件路径
     * @return 是否成功分发密钥
     */
    boolean distributeServerKeys(String keyFilePath);
}
