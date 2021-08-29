package com.qingyun.springframework.core.convert.support;

import com.qingyun.springframework.core.convert.converter.ConverterRegistry;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-28 21:29
 **/
public class DefaultConversionService extends GenericConversionService{

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        // 添加各类的转换器和类型转换工厂
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
        converterRegistry.addConverter(new StringToIntegerConverter());
    }

}
