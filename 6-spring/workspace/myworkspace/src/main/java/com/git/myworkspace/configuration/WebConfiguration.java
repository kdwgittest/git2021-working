package com.git.myworkspace.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
				// ������å�� ������ ���ҽ�
				.addMapping("/**") // ��ü���ҽ��� ���
				// ������å�� ����� ������(Ŭ���̾�Ʈ�ּ�)���
				// ������: html������ ������ ������ �ּ�
				// html�������� ��� ������ �޾ƿԴ��� ����ϰ�����
				.allowedOrigins("http://localhost:3000", "http://ec2-3-36-117-144.ap-northeast-2.compute.amazonaws.com")
				// ������å���� ����� HTTP�޼���
				.allowedMethods("*"); // ��ü�޼��带 ���
	}
}
