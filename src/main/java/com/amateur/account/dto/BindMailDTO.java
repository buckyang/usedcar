package com.amateur.account.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class BindMailDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String mail;

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
