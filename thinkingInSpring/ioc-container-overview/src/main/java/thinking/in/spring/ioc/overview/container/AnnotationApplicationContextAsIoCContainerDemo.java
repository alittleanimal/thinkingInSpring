package thinking.in.spring.ioc.overview.container;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import thinking.in.spring.ioc.overview.domain.User;

import java.util.Map;

@Configuration
public class AnnotationApplicationContextAsIoCContainerDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 AnnotationApplicationContextAsIoCContainerDemo 作为配置类（Configuration Class）
        applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找集合对象
        lookUpCollectionByType(applicationContext);

        // 关闭应用上下文
        applicationContext.close();
    }

    /**
     * 通过 Java 注解的方式，定义了一个 Bean
     *
     * @return
     */
    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("Adrian");
        return user;
    }

    private static void lookUpCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有 User 集合对象： " + users);
        }
    }
}
