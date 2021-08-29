package com.qingyun.springframework.core.convert.converter;

/**
 * @description： 类型转化工厂
 * @author: 張青云
 * @create: 2021-08-28 21:05
 **/
public interface ConverterFactory<S, R>{

    /**
     * 获取一个类型转换器，将S转换成T（T实现了R）
     */
    <T extends R> Converter<S, T> getConverter(Class<T> targetType);

}
