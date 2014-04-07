package com.amateur.admin.account.dto;

import org.hibernate.validator.constraints.NotBlank;

public class AdminLoginDTO {
	@NotBlank
	private String loginName;
	@NotBlank
	private String password;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
