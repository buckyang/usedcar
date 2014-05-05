package com.amateur.account.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class ForgetPasswordDTO implements Serializable{

	private static final long serialVersionUID = -4117489789775771073L;
	
	@NotBlank
	private String loginName;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}
