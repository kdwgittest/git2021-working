package com.git.hellocilent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class HelloClientController {

	private HelloClientService service;

	private Map<String, String> clientConnectedMap = new HashMap<String, String>();

	@Autowired
	public HelloClientController(HelloClientService service) {
		this.service = service;
	}

	@GetMapping(value = "/event/{clientId}")
	public SseEmitter connectEvent(@PathVariable String clientId) {
		// Event 를 발생시키는 객체를 생성
		SseEmitter emitter = new SseEmitter();
		if (emitter != null) {
			service.removeEmitter(clientId);
		}

		emitter = new SseEmitter(-1L);

		service.putEmitter(clientId, emitter);

		try {
			emitter.send("connected");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return emitter;
	}

}
