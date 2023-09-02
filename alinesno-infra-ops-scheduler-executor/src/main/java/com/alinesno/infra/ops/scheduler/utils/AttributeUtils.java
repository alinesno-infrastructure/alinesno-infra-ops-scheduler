package com.alinesno.infra.ops.scheduler.utils;

import com.alinesno.infra.ops.scheduler.dto.ValueAttributeDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 属性工具类，用于处理属性相关操作。
 */
public class AttributeUtils {
    /**
     * 将ValueAttributeDto对象列表转换为Map<String, Object>。
     *
     * @param attributes ValueAttributeDto对象列表
     * @return 转换后的属性Map
     */
    public static Map<String, Object> convertAttributesToMap(List<ValueAttributeDto> attributes) {
        Map<String, Object> attributesMap = new HashMap<>();
        for (ValueAttributeDto attribute : attributes) {
            attributesMap.put(attribute.getKey(), attribute.getValue());
        }
        return attributesMap;
    }

    /**
     * 使用提供的键和值创建一个包含单个属性的Map<String, Object>。
     *
     * @param key   属性键
     * @param value 属性值
     * @return 创建的属性Map
     */
    public static Map<String, Object> createAttributeMap(String key, String value) {
        Map<String, Object> attributeMap = new HashMap<>();
        attributeMap.put(key, value);
        return attributeMap;
    }
}
