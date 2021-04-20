package com.henry.selfConfig_and_autoConfig.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
// 1 可以指定一组属性的前缀；
// 2 可以指定属性文件所在的位置 - 比如，如果属性文件在类路径的/config目录下 locations = {"classpath:config/author.properties"}
@ConfigurationProperties(prefix = "author")
public class AuthorSetting {
    // 预期这些字段的值会被自动映射成功
    private String name; // 字段名与属性名相同
    private Long age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

}
