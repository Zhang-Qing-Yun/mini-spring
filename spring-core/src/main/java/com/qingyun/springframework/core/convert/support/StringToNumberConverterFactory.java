package com.qingyun.springframework.core.convert.support;

import com.qingyun.springframework.core.convert.converter.Converter;
import com.qingyun.springframework.core.convert.converter.ConverterFactory;
import com.qingyun.springframework.util.NumberUtils;
import com.sun.istack.internal.Nullable;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-28 21:31
 **/
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        @Nullable
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }

}
