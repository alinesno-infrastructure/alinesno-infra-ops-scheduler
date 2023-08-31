package com.alinesno.infra.ops.scheduler.dto;

import java.io.Serializable;

/**
 * 任务信息
 */
public class TaskInfoDto implements Serializable {

    // taskInfo
    private String taskType ; // 任务类型
    private String name ;  // 任务名称
    private String describe ; // 任务描述

}