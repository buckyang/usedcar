package com.amateur.account.dto;

public class RegistrationDTO {
	private Integer	accountId;
	private String	password;
	private String	nickname;
	private String	email;
	private String	phoneVerifyCode;
	private Boolean	acceptTerm;



	public Integer getAccountId() {
		return accountId;
	}



	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhoneVerifyCode() {
		return phoneVerifyCode;
	}



	public void setPhoneVerifyCode(String phoneVerifyCode) {
		this.phoneVerifyCode = phoneVerifyCode;
	}



	public Boolean getAcceptTerm() {
		return acceptTerm;
	}



	public void setAcceptTerm(Boolean acceptTerm) {
		this.acceptTerm = acceptTerm;
	}
}
