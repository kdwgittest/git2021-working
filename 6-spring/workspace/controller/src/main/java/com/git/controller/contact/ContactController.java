package com.git.controller.contact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

	private SortedMap<Long, Contact> contacts = Collections
			.synchronizedSortedMap(new TreeMap<Long, Contact>(Collections.reverseOrder()));

	private AtomicLong maxId = new AtomicLong();

	// contact 목록조회
	@GetMapping(value = "/contact")
	public List<Contact> getContacts() {
		return new ArrayList<Contact>(contacts.values());
	}

	// contact 추가
	@PostMapping(value = "/contact")
	public Contact addContact(@RequestBody Contact contact, HttpServletResponse res) {
		// name 추가
		System.out.println(contact);
		if (contact.getUsername() == null || contact.getUsername().isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		String username = getPlainText(contact.getUsername());
		if (username.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// phone 추가

		System.out.println(contact);
		if (contact.getTellnumber() == null || contact.getTellnumber().isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		String tellnumber = getPlainText(contact.getTellnumber());
		if (tellnumber.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// email 추가
		System.out.println(contact);
		if (contact.getMail() == null || contact.getMail().isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		String mail = getPlainText(contact.getMail());
		if (mail.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// id값 생성
		Long currentId = maxId.incrementAndGet();

		Contact contactItem = Contact.builder().id(currentId).username(username).tellnumber(tellnumber).mail(mail)
				.createdTime(new Date().getTime()).build();
		// 목록객체 추가
		contacts.put(currentId, contactItem);

		res.setStatus(HttpServletResponse.SC_CREATED);
		System.out.println(contactItem);
		// 추가된 객체를 반환
		return contactItem;

	}

	// 1건 삭제
	@DeleteMapping(value = "/contact")
	public boolean removeContact(@PathVariable long id, HttpServletResponse res) {
		// 해당 id의 데이터 한건을 가져옴
		Contact contact = contacts.get(Long.valueOf(id));
		if (contact == null) {
			// 해당 id 데이터 없으면 404경고창 띄워줌
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// 삭제수행
		contacts.remove(Long.valueOf(id));
		return true;
	}

	// 1건 수정
	@PutMapping(value = "/contact")
	public Contact modifyContact(@PathVariable long id, @RequestBody Contact contact, HttpServletResponse res) {
		Contact findItem = contacts.get(Long.valueOf(id));
		if (findItem == null) {
			// 404 error
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// 데이터 검증 로직 , 값이 없으면 에러처리함
		if (contact.getUsername() == null || contact.getUsername().isEmpty()) {
			// 클라이언트에서 메모값이 없이 보내거나 빈값으로 보낸 것임
			// 클라이언트 오류, 4xx
			// 요청값을 잘못보낸 것임 - Bad Request (400)
			// res.setStatus(400);

			// Dispatcher Servlet이 생성한 응답객체에 status코드를 넣어줌
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		String username = getPlainText(contact.getUsername());
		if (username.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// phone
		if (contact.getTellnumber() == null || contact.getTellnumber().isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		String tellnumber = getPlainText(contact.getTellnumber());
		if (tellnumber.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// email
		if (contact.getMail() == null || contact.getMail().isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		String mail = getPlainText(contact.getMail());
		if (mail.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// 데이터 변경
		findItem.setUsername(username);
		findItem.setTellnumber(tellnumber);
		findItem.setMail(mail);

		return findItem;

	}

	private String getPlainText(String text) {
		return text.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	}

}
