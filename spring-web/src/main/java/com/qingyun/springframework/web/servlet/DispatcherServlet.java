package com.qingyun.springframework.web.servlet;

import cn.hutool.core.util.StrUtil;
import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.annotation.Scope;
import com.qingyun.springframework.beans.factory.config.BeanDefinition;
import com.qingyun.springframework.context.support.ClassPathXmlApplicationContext;
import com.qingyun.springframework.web.annotation.Controller;
import com.qingyun.springframework.web.annotation.RequestMapping;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @description： 前端控制器，是整个SpringMVC框架的调度中心
 * @author: 張青云
 * @create: 2021-09-12 21:15
 **/
public class DispatcherServlet extends HttpServlet {
    //  保存SpringMVC.properties
    private Properties properties = new Properties();

    //  扫描指定包路径，得到该包路径下的所有类的名字
    private List<String> classNames = new ArrayList<>();

    //  url与处理该url的方法的匹配关系
    private Map<String, Method> handlerMapping = new HashMap<>();

    //  @Controller类
    private HashSet<Class> classes = new HashSet<>();

    //  url与处理该url的Controller的匹配关系
    private Map<String, Object> controllerMap = new HashMap<>();

    //  容器
    private ClassPathXmlApplicationContext xmlApplicationContext;

    @Override
    public void init(ServletConfig config) {
        try {
            //  初始化一个容器
            xmlApplicationContext = new ClassPathXmlApplicationContext("SpringMVC.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //  加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //  进行包扫描，路径是配置在SpringMVC中的包扫描路径
        doScanner(properties.getProperty("scanPackage"));
        //  对指定包中的@Controller类进行初始化，并放入到容器中
        doInstance();
        initHandlerMapping();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            //处理请求
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500!! Server Exception");
        }
    }

    public void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (handlerMapping.isEmpty()) return;
        String url = request.getRequestURI();
        String contextPath = request.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        //  handlerMapping中没有这个url则说明没有处理该url的Controller
        if (!handlerMapping.containsKey(url)) {
            response.getWriter().write("404 NOT FOUND!");
            return;
        }
        Method method = handlerMapping.get(url);
        Class<?>[] parameterTypes = method.getParameterTypes();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Object[] paramValues = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            String requestParam = parameterTypes[i].getSimpleName();
            if (requestParam.equals("HttpServletRequest")) {
                paramValues[i] = request;
                continue;
            }
            if (requestParam.equals("HttpServletResponse")) {
                paramValues[i] = response;
                continue;
            }
            if (requestParam.equals("String")) {
                for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
                    String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
                    paramValues[i] = value;
                }
            }
        }
        try {
            //  通过反射调用函数
            method.invoke(controllerMap.get(url), paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doLoadConfig(String location) {
        //把web.xml中的contextConfigLocation对应value值的文件加载到流里面
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(location);

        try {
            //用Properties文件加载文件里的内容
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != resourceAsStream) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 扫描该包路径下的所有类
     * @param packageName
     */
    private void doScanner(String packageName) {
        //  把所有的 . 替换成 /
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                //递归读取包
                doScanner(packageName + "." + file.getName());
            } else {
                String className = packageName + "." + file.getName().replace(".class", "");
                classNames.add(className);
            }
        }
    }

    /**
     * 实例化@Controller并将其添加到容器中
     */
    private void doInstance() {
        if (classNames.isEmpty()) {
            return;
        }
        for (String className : classNames) {
            try {
                //把类搞出来,反射来实例化(只有加@Controller需要实例化)
                Class clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(Controller.class)) {
                    classes.add(clazz);
                    BeanDefinition definition = new BeanDefinition(clazz);
                    // 解析 Bean 的作用域 singleton、prototype
                    String beanScope = resolveBeanScope(definition);
                    if (StrUtil.isNotEmpty(beanScope)) {
                        definition.setScope(beanScope);
                    } else {
                        throw new BeansException("Bean的作用域设置错误！");
                    }
                    //  向容器中注入Controller的beanDefinition
                    xmlApplicationContext.registerBeanDefinition(clazz.getName(), definition);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            xmlApplicationContext.reInstantiateSingletons();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将Controller可以处理哪些url解析出来
     */
    private void initHandlerMapping() {
        if (classes.isEmpty()) return;
        try {
            for (Class<?> clazz : classes) {
                String baseUrl = "";
                if (clazz.isAnnotationPresent(RequestMapping.class)) {
                    baseUrl = clazz.getAnnotation(RequestMapping.class).value();
                }
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (!method.isAnnotationPresent(RequestMapping.class)) continue;
                    String url = method.getAnnotation(RequestMapping.class).value();
                    url = (baseUrl + "/" + url).replaceAll("/+", "/");
                    handlerMapping.put(url, method);
                    controllerMap.put(url, xmlApplicationContext.getBean(clazz));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析Bean的作用域singleton、prototype
     */
    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (null != scope) {
            return scope.value();
        }
        return StrUtil.EMPTY;
    }
}
