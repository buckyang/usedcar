package com.amateur.account.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class RegistrationDTO {
	private Integer	accountId;
	@Size(min = 6)
	private String	password;
	@Size(min = 6)
	private String	repassword;
	@NotBlank
	private String	nickname;
	@Email
	private String	email;
	@Size(min = 6)
	private String phone;
	
	private String	phoneVerifyCode;
	@AssertTrue
	private Boolean	acceptTerm;
	private Integer	resellerType;
	private String	resellerName;
	@Range(min=1, max=2)
	private Integer	accountType;



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



	public Integer getResellerType() {
		return resellerType;
	}



	public void setResellerType(Integer resellerType) {
		this.resellerType = resellerType;
	}



	public String getResellerName() {
		return resellerName;
	}



	public void setResellerName(String resellerName) {
		this.resellerName = resellerName;
	}



	public Integer getAccountType() {
		return accountType;
	}



	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}



	public String getRepassword() {
		return repassword;
	}



	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}
}
