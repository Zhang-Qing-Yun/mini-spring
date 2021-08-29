import com.qingyun.springframework.aop.test.bean.IUserService;
import com.qingyun.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-24 01:08
 **/
public class AopTest {
    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
        //  可以发现对目标类拦截成功并进行了增强，事实上调用方法的过程是通过代理类来完成的（切点方法才增强，否则不增强）
    }
}
