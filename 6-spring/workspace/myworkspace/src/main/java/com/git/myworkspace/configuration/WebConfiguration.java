package com.git.myworkspace.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
				// 공유정책을 적용할 리소스
				.addMapping("/**") // 전체리소스를 허용
				// 공유정책을 허용할 오리진(클라이언트주소)목록
				// 오리진: html문서를 배포한 서버의 주소
				// html문서에는 어디서 문서를 받아왔는지 기록하고있음
				.allowedOrigins("http://localhost:3000", "http://ec2-3-36-117-144.ap-northeast-2.compute.amazonaws.com")
				// 공유정책으로 허용할 HTTP메서드
				.allowedMethods("*"); // 전체메서드를 허용
	}
}
