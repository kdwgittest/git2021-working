package com.git.myworkspace.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// contact 테이블에 접근하는 객체 

// 

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
