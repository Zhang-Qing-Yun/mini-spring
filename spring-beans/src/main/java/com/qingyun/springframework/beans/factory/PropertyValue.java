package com.qingyun.springframework.beans.factory;

/**
 * @description： bean实例中的一个属性
 * @author: 張青云
 * @create: 2021-08-18 23:47
 **/
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
