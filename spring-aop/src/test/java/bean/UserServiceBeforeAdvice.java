package bean;

import com.qingyun.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-24 01:06
 **/
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }

}
