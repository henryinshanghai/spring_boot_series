package com.henry.spring_security_demo.config;

import com.henry.spring_security_demo.security.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{//1 扩展Spring Security的配置 需要继承 WebSecurity_ConfigurerAdapter
	
	@Bean
	UserDetailsService customUserService(){ //2 注册 CustomUserService的Bean
		return new CustomUserService();
	}

	// 重写configure(auth)方法 - 设置进 自定义的UserDetailsService
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService()); //3 添加自定义的 user detail service认证
		
	}

	// 重写configure(http)方法 - 声明/定义 认证(需不需要登录) 与 权限(访问路径) 的规则
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
						.anyRequest().authenticated() // Ⅰ 对所有的请求，都需要认证(登录)后才能访问
						.and()
						.formLogin()
							.loginPage("/login") // 登陆页面的路径为/login
							.failureUrl("/login?error")
							.permitAll() // Ⅱ 定制登录行为：登录页面可以任意访问
						.and()
						.logout().permitAll(); // Ⅲ 定制注销行为：注销的请求可以任意访问
		

	}


}
