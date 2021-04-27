package com.henry.chapter7_06_p2p;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/login").permitAll() // 对 / 与 /login 这两个路径不进行拦截
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login") // 设置 登陆页面的访问路径为 /login
                .defaultSuccessUrl("/chat") // 设置 登陆成功后，转向到 /chat路径
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    // 在内存中配置两个用户
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("henry").password("{noop}henry").roles("USER")
                .and()
                .withUser("jennifer").password("{noop}jennifer").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**"); // 对于/static目录下的静态资源，Security不做拦截
    }
}
