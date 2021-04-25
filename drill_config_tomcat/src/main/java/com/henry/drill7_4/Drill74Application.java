package com.henry.drill7_4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Drill74Application {

    public static void main(String[] args) {
        SpringApplication.run(Drill74Application.class, args);
    }

    // 为Tomcat专门进行的配置
//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
//    factory.setPort(8888);
//    factory.setSessionTimeout(10, TimeUnit.MINUTES);
//    factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
//    return factory;
//    }

}
