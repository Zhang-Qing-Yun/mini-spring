package com.qingyun.springframework.core.convert.converter;

import cn.hutool.core.lang.Assert;

import java.util.Set;

/**
 * @description： 通用的类型转换器
 * @author: 張青云
 * @create: 2021-08-28 21:17
 **/
public interface GenericConverter {

    /**
     * 获取这个类型转换器可以完成的类型转换类型
     */
    Set<ConvertiblePair> getConvertibleTypes();

    /**
     * 进行数据类型转换
     */
    Object convert(Object source, Class sourceType, Class targetType);


    final class ConvertiblePair {
        private final Class<?> sourceType;

        private final Class<?> targetType;

        public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
            Assert.notNull(sourceType, "Source type must not be null");
            Assert.notNull(targetType, "Target type must not be null");
            this.sourceType = sourceType;
            this.targetType = targetType;
        }

        public Class<?> getSourceType() {
            return this.sourceType;
        }

        public Class<?> getTargetType() {
            return this.targetType;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != ConvertiblePair.class) {
                return false;
            }
            ConvertiblePair other = (ConvertiblePair) obj;
            return this.sourceType.equals(other.sourceType) && this.targetType.equals(other.targetType);

        }

        @Override
        public int hashCode() {
            return this.sourceType.hashCode() * 31 + this.targetType.hashCode();
        }
    }

}
