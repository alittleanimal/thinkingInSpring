package thinking.in.spring.bean.definition;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import thinking.in.spring.ioc.overview.domain.User;

public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        // 1. 通过 BeanDefinitionBuilder 构建
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 通过属性设置
        definitionBuilder.addPropertyValue("id", 1);
        definitionBuilder.addPropertyValue("name", "Adrian");
        // 获取 BeanDefinition 实例
        BeanDefinition beanDefinition = definitionBuilder.getBeanDefinition();
        // BeanDefinition 并非 Bean 终态， 可以自定义修改

        // 2. 通过AbstractBeanDefinition 以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        // 设置 Bean 类型
        genericBeanDefinition.setBeanClass(User.class);
        // 通过 MutablePropertyValues 批量操作属性
        MutablePropertyValues propertyValues = new MutablePropertyValues();
//        propertyValues.addPropertyValue("id", 1);
        propertyValues.add("id", 1).add("name", "Adrian");
        genericBeanDefinition.setPropertyValues(propertyValues);
    }
}
