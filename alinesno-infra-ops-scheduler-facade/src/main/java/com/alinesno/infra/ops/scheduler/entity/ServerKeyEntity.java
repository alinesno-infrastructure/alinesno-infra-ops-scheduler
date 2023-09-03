package com.alinesno.infra.ops.scheduler.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 服务器密钥实体类
 */
@TableName("server_key")
public class ServerKeyEntity extends InfraBaseEntity {
    @TableField("server_id")
    private Long serverId;

    @TableField("key")
    private String key;

    @TableField("type")
    private String type ; // 密钥类型

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取服务器ID
     */
    public Long getServerId() {
        return serverId;
    }

    /**
     * 设置服务器ID
     */
    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    /**
     * 获取服务器密钥
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置服务器密钥
     */
    public void setKey(String key) {
        this.key = key;
    }
}
