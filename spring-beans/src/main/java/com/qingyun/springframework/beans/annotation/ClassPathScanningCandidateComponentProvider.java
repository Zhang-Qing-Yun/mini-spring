package com.qingyun.springframework.beans.annotation;

import cn.hutool.core.util.ClassUtil;
import com.qingyun.springframework.beans.factory.config.BeanDefinition;
import com.qingyun.springframework.beans.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-24 18:14
 **/
public class ClassPathScanningCandidateComponentProvider {

    /**
     * 获取basePackage路径下标注@Component注解的类并为其创建BeanDefinition
     * @param basePackage
     * @return
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }

}
