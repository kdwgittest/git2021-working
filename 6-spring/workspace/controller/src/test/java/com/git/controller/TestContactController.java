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
		// �׽�Ʈ ���̽� ������ �غ�
		ContactController controller = new ContactController();
		Contact expected = Contact.builder().username("test").build();

		// ServletResponse ��ü�� ��¥(Mock)�� �־���
		controller.addContact(expected, new MockHttpServletResponse());

		// ��ü��� ������
		List<Contact> contacts = controller.getContacts();
		Contact actual = contacts.get(0);

		// ������ �����Ϳ� ���� �����͸� ����
		assertEquals(1, actual.getId());
		assertEquals(expected.getUsername(), actual.getUsername());

	}

	@Test
	void removeContact() {
		// ���������غ�
		ContactController controller = new ContactController();
		Contact testItem = Contact.builder().username("test").build();
		controller.addContact(testItem, new MockHttpServletResponse());
		// ������ ���ũ�� 1
		List<Contact> beforeContacts = controller.getContacts();
		assertEquals(1, beforeContacts.size());
		// id�� 1 �� ���� ���� (�׽�Ʈ ���̽�)
		controller.removeContact(1, new MockHttpServletResponse());
		// ������ ���ũ�� 0
		List<Contact> afterContacts = controller.getContacts();
		assertEquals(0, afterContacts.size());
	}

	@Test
	void modifyContact() {
		// �׽�Ʈ ������ �ҷ���
		ContactController controller = new ContactController();
		Contact testItem = Contact.builder().username("test").build();
		controller.addContact(testItem, new MockHttpServletResponse());
		// ������ �׽�Ʈ ������
		String expectedResult = "modify test memo";
		Contact modifyData = Contact.builder().username(expectedResult).build();

		HttpServletResponse res = new MockHttpServletResponse();
		// id�� 1�� contact�� memo�� ����
		controller.modifyContact(1, modifyData, res);

		// �������� ��������� ��
		List<Contact> contacts = controller.getContacts();
		assertEquals(expectedResult, contacts.get(0).getUsername());
		// id���� �ٸ���� , id 2�� �����غ�
		Contact resultContactId = controller.modifyContact(2, modifyData, res);
		// null �̸� 404 �˷���
		assertNull(resultContactId);
		assertEquals(HttpServletResponse.SC_NOT_FOUND, res.getStatus());

		// memo���� null �� ���
		Contact resultContactMemoNull = controller.modifyContact(1, new Contact(), res);
		// �������� ������� ��
		assertNull(resultContactMemoNull);
		assertEquals(HttpServletResponse.SC_BAD_REQUEST, res.getStatus());

		// memo���� �� ��("")�ΰ��
		Contact resultContactMemoEmpty = controller.modifyContact(1, Contact.builder().username("").build(), res);

		// then - �������� ��������� ��
		// ��ȯ ��ü�� null, Status Code 400
		assertNull(resultContactMemoEmpty);
		assertEquals(HttpServletResponse.SC_BAD_REQUEST, res.getStatus());

	}

}
