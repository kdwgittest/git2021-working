package com.git.controller.contact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

	private long id;
	private String username;
	private String tellnumber;
	private String mail;
	private long createdTime;

}
