package com.alinesno.infra.ops.scheduler.dto;

/**
 * 环境变量
 */
public class ValueAttributeDto {

    private String key ;
    private String value ;

    public ValueAttributeDto() {
    }

    public ValueAttributeDto(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
