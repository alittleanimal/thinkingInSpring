package thinking.in.spring.bean.definition;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import thinking.in.spring.ioc.overview.domain.User;

@Import(AnnotationBeanDefinitionDemo.Config.class) // 通过 @Import 来进行导入
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class （配置类）
        applicationContext.register(AnnotationBeanDefinitionDemo.class);

        // 通过 BeanDefinition 注册API实现
        // 1. 命名 Bean 的注册方式
        registerUserBeanDefinition(applicationContext, "user-adrian");
        // 2. 非命名 Bean 的注册方式
        registerUserBeanDefinition(applicationContext);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 1. 通过 @Bean 方式定义
        // 2. 通过 @Component 方式
        // 3. 通过 @Import 来进行导入

        //  按照类型依赖查找
        System.out.println("Config 类型的所有 Beans" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User 类型的所有 Beans" + applicationContext.getBeansOfType(User.class));


        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 1L).addPropertyValue("name", "Adrian");

        if (StringUtils.hasText(beanName)) {
            // 注册 BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            // 非命名 Bean 注册方法
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }

    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }

    @Component // 定义当前类作为 Spring Bean（组件）
    public static class Config {
        /**
         * 通过 Java 注解的方式，定义了一个 Bean
         *
         * @return
         */
        @Bean(name = {"user", "adrian-user"})
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("Adrian");
            return user;
        }
    }
}
