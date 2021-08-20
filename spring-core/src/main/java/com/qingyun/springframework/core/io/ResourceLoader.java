package com.qingyun.springframework.core.io;

/**
 * @description： 使用外观模式来屏蔽掉Resource接口实现系统的细节，使得客户端可以不用去了解有哪些Resource的实现类
 * @author: 張青云
 * @create: 2021-08-20 10:18
 **/
public interface ResourceLoader {

    //  类路径的前缀
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 根据用户输入的资源地址location获取一个对应的Resource
     * @param location 资源地址
     * @return Resource
     */
    Resource getResource(String location);
}
