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

	// 백앤드 부분
	private ContactRepository repo;

	@Autowired
	public ContactController(ContactRepository repo) {
		this.repo = repo;
	}

	// contact 목록조회
	@GetMapping(value = "/contact")
	public List<Contact> getContacts() throws InterruptedException {
//		return new ArrayList<Contact>(contacts.values());
		return repo.findAll(Sort.by("id").descending());

	}

	// contact 추가
	@PostMapping(value = "/contact")
	public Contact addContact(@RequestBody Contact contact, HttpServletResponse res) throws InterruptedException {

		if (TextProcesser.isEmpyText(contact.getTitle())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		Contact contactItem = Contact.builder().title(contact.getTitle())
				.description(TextProcesser.getPlainText(contact.getDescription())).username(contact.getUsername())
				.tellnumber(contact.getTellnumber()).mail(contact.getMail()).createdTime(new Date().getTime()).build();
		// 목록객체 추가
//		contacts.put(currentId, contactItem);

		Contact contactSaved = repo.save(contactItem);

		res.setStatus(HttpServletResponse.SC_CREATED);
		System.out.println(contactItem);
		// 추가된 객체를 반환
		return contactSaved;

	}

	// 1건 삭제
	@DeleteMapping(value = "/contact")
	public boolean removeContact(@PathVariable long id, HttpServletResponse res) throws InterruptedException {
		// 해당 id의 데이터 한건을 가져옴
//		Contact contact = contacts.get(Long.valueOf(id));
//		if (contact == null) {
//			// 해당 id 데이터 없으면 404경고창 띄워줌
//			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
//			return false;
//		}

		// 백앤드 repo
		Optional<Contact> contact = repo.findById(id);
		if (contact.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// 삭제수행
//		contacts.remove(Long.valueOf(id));
		repo.deleteById(id); // 백앤드 repo
		return true;
	}

	// 1건 수정
	@PutMapping(value = "/contact/{id}")
	public Contact modifyContact(@PathVariable long id, @RequestBody Contact contact, HttpServletResponse res)
			throws InterruptedException {

		Optional<Contact> contactItem = repo.findById(id);
		if (contactItem.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// 타이틀이 빈값
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
