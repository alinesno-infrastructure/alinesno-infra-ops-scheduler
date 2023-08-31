package com.alinesno.infra.ops.scheduler.dto;

public class ExecutorScriptDto {

    private String type ; // 脚本类型
    private String scriptContent; // 脚本

    public ExecutorScriptDto() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScriptContent() {
        return scriptContent;
    }

    public void setScriptContent(String scriptContent) {
        this.scriptContent = scriptContent;
    }

    @Override
    public String toString() {
        return "ExecutorScriptDto{" +
                "type='" + type + '\'' +
                ", scriptContent='" + scriptContent + '\'' +
                '}';
    }
}
