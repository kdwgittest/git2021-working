package com.git.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootConfiguration: 의존성 중닙을 할 수 있도록 설정해줌 
//@EnableAutoConfiguration: 사용하는 라이브러리에 따라 자동으로 환경을 구성함
// -> spring-boot-starter-web: embede Tomcat 웹서버를 구동함, 8080포트 응답대기
//@ComponentScan: 컴포넌트들을 검색하여 싱글턴으로 객체생성을 함   
// -> Spring Framework 에서 컴포넌트(예- @Controller) 어노테이션이 있는 클래스들을 검색함 
// -> Spring Framework 에서 싱글턴으로 객체를 생성함 

@SpringBootApplication
public class HelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

}
