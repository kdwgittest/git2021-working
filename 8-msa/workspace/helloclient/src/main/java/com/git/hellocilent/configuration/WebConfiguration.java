package com.git.hellocilent.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // 전체리소스를 허용 (/todos, /contacts...)
				// 공유정책을 허용할 오리진목록
				.allowedOrigins("http://localhost:3000", "http://ec2-3-36-117-144.ap-northeast-2.compute.amazonaws.com")
				.allowedMethods("*"); // 전체메서드를 허용
	}
}
