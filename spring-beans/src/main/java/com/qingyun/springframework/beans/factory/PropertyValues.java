package com.qingyun.springframework.beans.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * @description： 一个bean对象中全部属性的集合
 * @author: 張青云
 * @create: 2021-08-18 23:48
 **/
public class PropertyValues {
    //  用来保存属性
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        //  检查是否已有该属性
        for (int i = 0; i < this.propertyValueList.size(); i++) {
            PropertyValue currentPv = this.propertyValueList.get(i);
            if (currentPv.getName().equals(pv.getName())) {
                // 覆盖原有的属性值
                this.propertyValueList.set(i, pv);
                return;
            }
        }
        this.propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);  // list的toArray方法需要传入一个数组来指定类型
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }
}
