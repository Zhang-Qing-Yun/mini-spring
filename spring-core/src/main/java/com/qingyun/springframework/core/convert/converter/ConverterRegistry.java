package com.qingyun.springframework.core.convert.converter;

/**
 * @description： 类型转换注册接口
 * @author: 張青云
 * @create: 2021-08-28 21:08
 **/
public interface ConverterRegistry {

    /**
     * 向注册表中注册一个转换器
     */
    void addConverter(Converter<?, ?> converter);

    /**
     * 添加一个通用注册器
     */
    void addConverter(GenericConverter converter);

    /**
     * 增加一个类型转换工厂
     */
    void addConverterFactory(ConverterFactory<?, ?> converterFactory);

}
