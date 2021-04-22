package com.henry;

/**
 * 创建一个POJO类型，用作为判断依据 - 判断这个类型在classpath中是不是存在，存在则使用Spring容器来创建它的bean实例
 */
public class HelloService_03 {

    private String msg;

    public String sayHello() {
        return "Hello" + msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
