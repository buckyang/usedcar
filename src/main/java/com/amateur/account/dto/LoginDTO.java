package com.amateur.account.dto;

import org.hibernate.validator.constraints.NotBlank;

public class LoginDTO {
	@NotBlank
	private String	phoneOrEmail;
	@NotBlank
	private String	password;



	public String getPhoneOrEmail() {
		return phoneOrEmail;
	}



	public void setPhoneOrEmail(String phoneOrEmail) {
		this.phoneOrEmail = phoneOrEmail;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}

}
