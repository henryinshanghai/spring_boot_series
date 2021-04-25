package com.henry.drill7_4;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component // 定义成为Spring容器中的Bean
public class CustomServletContainer implements EmbeddedServletContainerCustomizer{ // 实现指定的接口：EmbeddedServletContainerCustomizer

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		// 配置 Servlet容器的端口号
//		container.setPort(8888);
		// 配置 发生错误时的错误页面
		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
		// 配置 Servlet用户会话的过期时间
		container.setSessionTimeout(10, TimeUnit.MINUTES);
		
	}
	


}
