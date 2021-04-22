package com.henry;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 创建用于映射属性的POJO类型
 */
@ConfigurationProperties(prefix = "hello") // 指定属性文件中 属性名称的前缀
public class HelloServiceProperties_02 {

    private static final String MSG = "world";

    private String msg = MSG; // 结合prefix，会从 属性文件中获取 hello.msg属性的值，然后映射到msg字段上

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
