package com.git.myworkspace.photo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

// 영속화: 휘발성 데이터 -> 비휘발성 장치로 

// ORM (Object Relational Mapping)
// : 객체를 테이블과 맵핑한 것 
// 1. 객체지향으로 개발할 수 있게함 
// 2. 특정 DB에 종속되지 않게함 

// @Entity: 테이블 클래스를 맵핑함 
// 기본방법은 Photo(pascal-case) -> photo(snake-case) 

@Entity
public class Photo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	// BLOB: binary large object
	@Column(columnDefinition = "VARCHAR(1000)")
	private String description;
	// BLOB: binary large object
	@Column(columnDefinition = "TEXT")
	private String photoUrl;
	private String fileType;
	private String fileName;
	private long createdTime;
}
