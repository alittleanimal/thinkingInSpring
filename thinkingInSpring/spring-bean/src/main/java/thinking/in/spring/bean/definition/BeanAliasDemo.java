package thinking.in.spring.bean.definition;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import thinking.in.spring.ioc.overview.domain.User;

public class BeanAliasDemo {
    public static void main(String[] args) {
        // 配置 xml 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/bean-definitions-context.xml");

        // 通过别名 adrian-user 获取曾用名 user 的 bean
        User user = beanFactory.getBean("user", User.class);
        User adrianUser = beanFactory.getBean("adrian-user", User.class);

        System.out.println("是否相同: " + (user == adrianUser));
    }
}
