package com.alinesno.infra.ops.scheduler.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 服务器实体类
 */
@TableName("server")
public class ServerEntity extends InfraBaseEntity {
    @TableField("id")
    private Long id;

    @TableField("name")
    private String name;

    @TableField("ip_address")
    private String ipAddress;

    @TableField("operating_system")
    private String operatingSystem;

    @TableField("cpu")
    private String cpu;

    @TableField("memory")
    private String memory;

    @TableField("storage")
    private String storage;

    @TableField("status")
    private String status;

    @TableField("group_id")
    private Long groupId;

    /**
     * 获取服务器ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置服务器ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取服务器名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置服务器名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取服务器IP地址
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 设置服务器IP地址
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * 获取操作系统
     */
    public String getOperatingSystem() {
        return operatingSystem;
    }

    /**
     * 设置操作系统
     */
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    /**
     * 获取CPU信息
     */
    public String getCpu() {
        return cpu;
    }

    /**
     * 设置CPU信息
     */
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    /**
     * 获取内存信息
     */
    public String getMemory() {
        return memory;
    }

    /**
     * 设置内存信息
     */
    public void setMemory(String memory) {
        this.memory = memory;
    }

    /**
     * 获取存储信息
     */
    public String getStorage() {
        return storage;
    }

    /**
     * 设置存储信息
     */
    public void setStorage(String storage) {
        this.storage = storage;
    }

    /**
     * 获取服务器状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置服务器状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取服务器组ID
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * 设置服务器组ID
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
