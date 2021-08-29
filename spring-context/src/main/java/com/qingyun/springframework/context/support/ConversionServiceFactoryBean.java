package com.qingyun.springframework.context.support;

import com.qingyun.springframework.beans.factory.FactoryBean;
import com.qingyun.springframework.beans.factory.InitializingBean;
import com.qingyun.springframework.core.convert.ConversionService;
import com.qingyun.springframework.core.convert.converter.Converter;
import com.qingyun.springframework.core.convert.converter.ConverterFactory;
import com.qingyun.springframework.core.convert.converter.ConverterRegistry;
import com.qingyun.springframework.core.convert.converter.GenericConverter;
import com.qingyun.springframework.core.convert.support.DefaultConversionService;
import com.qingyun.springframework.core.convert.support.GenericConversionService;
import com.sun.istack.internal.Nullable;

import java.util.Set;

/**
 * @description： 提供创建 ConversionService 工厂
 * @author: 張青云
 * @create: 2021-08-28 21:37
 **/
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    //  类型转换器集合
    @Nullable
    private Set<?> converters;

    @Nullable
    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.conversionService = new DefaultConversionService();
        //  添加默认的类型转换器
        DefaultConversionService.addDefaultConverters(this.conversionService);
        //  添加用户自定义的类型转换器
        registerConverters(converters, conversionService);
    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry) {
        if (converters != null) {
            for (Object converter : converters) {
                if (converter instanceof GenericConverter) {
                    registry.addConverter((GenericConverter) converter);
                } else if (converter instanceof Converter<?, ?>) {
                    registry.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?, ?>) {
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalArgumentException("Each converter object must implement one of the " +
                            "Converter, ConverterFactory, or GenericConverter interfaces");
                }
            }
        }
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }

}
