package com.git.myworkspace.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// contact ���̺� �����ϴ� ��ü 

// 

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
