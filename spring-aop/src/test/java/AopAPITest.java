import bean.IUserService;
import bean.UserService;
import bean.UserServiceInterceptor;
import com.qingyun.springframework.aop.AdvisedSupport;
import com.qingyun.springframework.aop.TargetSource;
import com.qingyun.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.qingyun.springframework.aop.framework.CglibAopProxy;
import com.qingyun.springframework.aop.framework.JdkDynamicAopProxy;
import org.junit.Test;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-23 22:07
 **/
public class AopAPITest {
    @Test
    public void test_dynamic() {
        // 目标对象
        IUserService userService = new UserService();

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* bean.IUserService.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_jdk.queryUserInfo());
        System.out.println();

        // 代理对象(CglibAopProxy)
        IUserService proxy_cglib = (IUserService) new CglibAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.register("青云"));
    }
}
