package com.git.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.git.controller.contact.Contact;
import com.git.controller.contact.ContactController;

@SpringBootTest
public class TestContactController {

	@Test
	void addContact() {
		// 테스트 케이스 데이터 준비
		ContactController controller = new ContactController();
		Contact expected = Contact.builder().username("test").build();

		// ServletResponse 객체는 가짜(Mock)을 넣어줌
		controller.addContact(expected, new MockHttpServletResponse());

		// 전체목록 가져옴
		List<Contact> contacts = controller.getContacts();
		Contact actual = contacts.get(0);

		// 예상결과 데이터와 실제 데이터를 비교함
		assertEquals(1, actual.getId());
		assertEquals(expected.getUsername(), actual.getUsername());

	}

	@Test
	void removeContact() {
		// 사전조건준비
		ContactController controller = new ContactController();
		Contact testItem = Contact.builder().username("test").build();
		controller.addContact(testItem, new MockHttpServletResponse());
		// 삭제전 목록크기 1
		List<Contact> beforeContacts = controller.getContacts();
		assertEquals(1, beforeContacts.size());
		// id가 1 인 것을 삭제 (테스트 케이스)
		controller.removeContact(1, new MockHttpServletResponse());
		// 삭제후 목록크기 0
		List<Contact> afterContacts = controller.getContacts();
		assertEquals(0, afterContacts.size());
	}

	@Test
	void modifyContact() {
		// 테스트 데이터 불러옴
		ContactController controller = new ContactController();
		Contact testItem = Contact.builder().username("test").build();
		controller.addContact(testItem, new MockHttpServletResponse());
		// 변경할 테스트 데이터
		String expectedResult = "modify test memo";
		Contact modifyData = Contact.builder().username(expectedResult).build();

		HttpServletResponse res = new MockHttpServletResponse();
		// id가 1인 contact에 memo를 수정
		controller.modifyContact(1, modifyData, res);

		// 예상결과와 실제결과를 비교
		List<Contact> contacts = controller.getContacts();
		assertEquals(expectedResult, contacts.get(0).getUsername());
		// id값이 다른경우 , id 2로 수정해봄
		Contact resultContactId = controller.modifyContact(2, modifyData, res);
		// null 이면 404 알려줌
		assertNull(resultContactId);
		assertEquals(HttpServletResponse.SC_NOT_FOUND, res.getStatus());

		// memo값이 null 일 경우
		Contact resultContactMemoNull = controller.modifyContact(1, new Contact(), res);
		// 예상결과와 실제결과 비교
		assertNull(resultContactMemoNull);
		assertEquals(HttpServletResponse.SC_BAD_REQUEST, res.getStatus());

		// memo값이 빈 값("")인경우
		Contact resultContactMemoEmpty = controller.modifyContact(1, Contact.builder().username("").build(), res);

		// then - 예상결과와 실제결과를 비교
		// 반환 객체가 null, Status Code 400
		assertNull(resultContactMemoEmpty);
		assertEquals(HttpServletResponse.SC_BAD_REQUEST, res.getStatus());

	}

}
