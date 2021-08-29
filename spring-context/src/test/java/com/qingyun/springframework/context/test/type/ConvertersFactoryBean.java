package com.qingyun.springframework.context.test.type;

import com.qingyun.springframework.beans.factory.FactoryBean;

import java.util.HashSet;
import java.util.Set;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-28 21:57
 **/
public class ConvertersFactoryBean implements FactoryBean<Set<?>> {

    @Override
    public Set<?> getObject() throws Exception {
        HashSet<Object> converters = new HashSet<>();
        StringToLocalDateConverter stringToLocalDateConverter = new StringToLocalDateConverter("yyyy-MM-dd");
        converters.add(stringToLocalDateConverter);
        return converters;
    }

    @Override
    public Class<?> getObjectType() {
        return Set.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}