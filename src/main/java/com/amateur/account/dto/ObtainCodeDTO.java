package com.amateur.account.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class ObtainCodeDTO implements Serializable {

	private static final long serialVersionUID = 2115948593331689141L;

	@NotBlank
	private String phoneNumber;

	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
