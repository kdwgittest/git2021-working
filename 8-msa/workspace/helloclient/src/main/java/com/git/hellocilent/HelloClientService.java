package com.git.hellocilent;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class HelloClientService {

	private Map<String, SseEmitter> emitters = new ConcurrentHashMap<String, SseEmitter>();

	public void putEmitter(String clientId, SseEmitter emitter) {
		this.emitters.put(clientId, emitter);
		System.out.println(emitters.size());
	}

	public SseEmitter getEmitter(String clientId) {
		return this.emitters.get(clientId);
	}

	public void removeEmitter(String clientId) {
		this.emitters.remove(clientId);
	}

	// 본인수신번호
	@RabbitListener(queues = "test.hello.2")
	public void receiveMassage(String message) throws UnsupportedEncodingException {
		System.out.println("-- test.hello.2 --");
		System.out.println(message);

		emitters.values().parallelStream().forEach(emitter -> {
			try {
				emitter.send(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}
}
