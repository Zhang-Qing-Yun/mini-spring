package com.qingyun.springframework.util;

/**
 * @description： Class相关的工具类
 * @author: 張青云
 * @create: 2021-08-20 10:11
 **/
public class ClassUtils {
    /**
     * 获取默认的类加载器
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    /**
     * 判断是否是由CGLIB创建的
     */
    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    /**
     * 该className是否是由CGLIB创建的Class的名字
     */
    public static boolean isCglibProxyClassName(String className) {
        return (className != null && className.contains("$$"));
    }
}
