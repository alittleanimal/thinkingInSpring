package thinking.in.spring.ioc.overview.dependency.injection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import thinking.in.spring.ioc.overview.repository.UserRepository;

/**
 * 依赖注入示例
 * 1. 通过名称查找
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        // 配置 xml 配置文件
        // 启动 Spring 应用上下文
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        // 依赖来源一： 自定义 Bean
        UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);
//        System.out.println(userRepository.getUsers());

        // 依赖来源二： 依赖注入（内建依赖）
        System.out.println(userRepository.getBeanFactory());
        ObjectFactory useFactory = userRepository.getObjectFactory();
        System.out.println(useFactory.getObject() == applicationContext);

        //  依赖查找（错误）
//        System.out.println(beanFactory.getBean(BeanFactory.class));

        // 依赖来源三： 容器内建 Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println("获取 Environment 类型的 Bean: " + environment);

        whoIsIoCContainer(userRepository, applicationContext);
    }

    private static void whoIsIoCContainer(UserRepository userRepository, ApplicationContext applicationContext) {

        // ConfigurableApplicationContext <= ApplicationContext <- BeanFactory

        // 为什么这个表达式不成立
        System.out.println(userRepository.getBeanFactory() == applicationContext);

        // Application is a BeanFactory
    }

}
