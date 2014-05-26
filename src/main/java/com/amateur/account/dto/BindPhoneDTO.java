package com.amateur.account.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class BindPhoneDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String phoneNumber;

	@NotBlank
	private String code;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
