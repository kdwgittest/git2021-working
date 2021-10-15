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

	// contact �����ȸ
	@GetMapping(value = "/contact")
	public List<Contact> getContacts() {
		return new ArrayList<Contact>(contacts.values());
	}

	// contact �߰�
	@PostMapping(value = "/contact")
	public Contact addContact(@RequestBody Contact contact, HttpServletResponse res) {
		// name �߰�
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

		// phone �߰�

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

		// email �߰�
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

		// id�� ����
		Long currentId = maxId.incrementAndGet();

		Contact contactItem = Contact.builder().id(currentId).username(username).tellnumber(tellnumber).mail(mail)
				.createdTime(new Date().getTime()).build();
		// ��ϰ�ü �߰�
		contacts.put(currentId, contactItem);

		res.setStatus(HttpServletResponse.SC_CREATED);
		System.out.println(contactItem);
		// �߰��� ��ü�� ��ȯ
		return contactItem;

	}

	// 1�� ����
	@DeleteMapping(value = "/contact")
	public boolean removeContact(@PathVariable long id, HttpServletResponse res) {
		// �ش� id�� ������ �Ѱ��� ������
		Contact contact = contacts.get(Long.valueOf(id));
		if (contact == null) {
			// �ش� id ������ ������ 404���â �����
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// ��������
		contacts.remove(Long.valueOf(id));
		return true;
	}

	// 1�� ����
	@PutMapping(value = "/contact")
	public Contact modifyContact(@PathVariable long id, @RequestBody Contact contact, HttpServletResponse res) {
		Contact findItem = contacts.get(Long.valueOf(id));
		if (findItem == null) {
			// 404 error
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// ������ ���� ���� , ���� ������ ����ó����
		if (contact.getUsername() == null || contact.getUsername().isEmpty()) {
			// Ŭ���̾�Ʈ���� �޸��� ���� �����ų� ������ ���� ����
			// Ŭ���̾�Ʈ ����, 4xx
			// ��û���� �߸����� ���� - Bad Request (400)
			// res.setStatus(400);

			// Dispatcher Servlet�� ������ ���䰴ü�� status�ڵ带 �־���
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

		// ������ ����
		findItem.setUsername(username);
		findItem.setTellnumber(tellnumber);
		findItem.setMail(mail);

		return findItem;

	}

	private String getPlainText(String text) {
		return text.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	}

}
