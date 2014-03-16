package com.amateur.account.dto;

import java.io.Serializable;
import java.util.Date;

public class ManagerUserInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer accountId;
	private String nickname;
	private String realName;
	private String email;
	private Boolean bindEmail;
	private Boolean sex;
	private String idNumber;
	private String phone;
	private Boolean bindPhone;
	private Date birthdate;
	private String certificateType;
	private String certificateNumber;
	private HomeAddressDTO homeAddress;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getBindEmail() {
		return bindEmail;
	}

	public void setBindEmail(Boolean bindEmail) {
		this.bindEmail = bindEmail;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getBindPhone() {
		return bindPhone;
	}

	public void setBindPhone(Boolean bindPhone) {
		this.bindPhone = bindPhone;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public HomeAddressDTO getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(HomeAddressDTO homeAddress) {
		this.homeAddress = homeAddress;
	}

}
