package com.git.myworkspace.contact;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.git.myworkspace.lib.TextProcesser;

@RestController
public class ContactController {

	// ��ص� �κ�
	private ContactRepository repo;

	@Autowired
	public ContactController(ContactRepository repo) {
		this.repo = repo;
	}

	// contact �����ȸ
	@GetMapping(value = "/contact")
	public List<Contact> getContacts() throws InterruptedException {
//		return new ArrayList<Contact>(contacts.values());
		return repo.findAll(Sort.by("id").descending());

	}

	// contact �߰�
	@PostMapping(value = "/contact")
	public Contact addContact(@RequestBody Contact contact, HttpServletResponse res) throws InterruptedException {

		if (TextProcesser.isEmpyText(contact.getTitle())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		Contact contactItem = Contact.builder().title(contact.getTitle())
				.description(TextProcesser.getPlainText(contact.getDescription())).username(contact.getUsername())
				.tellnumber(contact.getTellnumber()).mail(contact.getMail()).createdTime(new Date().getTime()).build();
		// ��ϰ�ü �߰�
//		contacts.put(currentId, contactItem);

		Contact contactSaved = repo.save(contactItem);

		res.setStatus(HttpServletResponse.SC_CREATED);
		System.out.println(contactItem);
		// �߰��� ��ü�� ��ȯ
		return contactSaved;

	}

	// 1�� ����
	@DeleteMapping(value = "/contact")
	public boolean removeContact(@PathVariable long id, HttpServletResponse res) throws InterruptedException {
		// �ش� id�� ������ �Ѱ��� ������
//		Contact contact = contacts.get(Long.valueOf(id));
//		if (contact == null) {
//			// �ش� id ������ ������ 404���â �����
//			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
//			return false;
//		}

		// ��ص� repo
		Optional<Contact> contact = repo.findById(id);
		if (contact.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// ��������
//		contacts.remove(Long.valueOf(id));
		repo.deleteById(id); // ��ص� repo
		return true;
	}

	// 1�� ����
	@PutMapping(value = "/contact/{id}")
	public Contact modifyContact(@PathVariable long id, @RequestBody Contact contact, HttpServletResponse res)
			throws InterruptedException {

		Optional<Contact> contactItem = repo.findById(id);
		if (contactItem.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// Ÿ��Ʋ�� ��
		if (TextProcesser.isEmpyText(contact.getTitle())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		Contact contactToSave = contactItem.get();

		contactToSave.setTitle(contact.getTitle());
		contactToSave.setUsername(contact.getUsername());
		contactToSave.setTellnumber(contact.getTellnumber());
		contactToSave.setMail(contact.getMail());
		contactToSave.setDescription(TextProcesser.getPlainText(contact.getDescription()));

		Contact contactSaved = repo.save(contactToSave);

		return contactSaved;

//	private String getPlainText(String text) {
//		return text.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
//	}

	}
}
