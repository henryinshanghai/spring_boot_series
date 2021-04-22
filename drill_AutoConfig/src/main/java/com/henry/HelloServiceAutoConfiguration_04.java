package com.henry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 创建 用来配置Bean的类型
 */
@Configuration // 声明这是一个用于配置Bean的java POJO类型
@EnableConfigurationProperties(HelloServiceProperties_02.class) // 指定 接收属性值的映射类型
@ConditionalOnClass(HelloService_03.class) // 指定 条件 - 当类路径中存在特定的类型时...
@ConditionalOnProperty(prefix = "hello", value = "enabled", matchIfMissing = true) // 指定条件2 - 属性文件中存在"hello=enabled"这样的键值对，如果不存在，默认条件为true
public class HelloServiceAutoConfiguration_04 {

    // 注入 属性映射类型
    @Autowired
    private HelloServiceProperties_02 helloServiceProperties02;

    // 定义创建Bean实例的方法
    @Bean
    @ConditionalOnMissingBean(HelloService_03.class)
    public HelloService_03 helloService03() {
        HelloService_03 helloService03 = new HelloService_03();

        helloService03.setMsg(helloServiceProperties02.getMsg());

        return helloService03;
    }
}
