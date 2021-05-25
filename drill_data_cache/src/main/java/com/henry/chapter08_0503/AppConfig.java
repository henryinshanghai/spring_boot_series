package com.henry.chapter08_0503;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching // 在配置类上，通过 @EnableCaching注解 来开启Spring Boot对于缓存的支持
public class AppConfig {

}
