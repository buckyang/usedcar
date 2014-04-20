package com.amateur.account.dto;

import java.io.Serializable;

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
	private String birthyear;
	private String birthmonth;
	private String birthday;
	private String certificateType;
	private String certificateNumber;
	private String province;
	private String city;
	private String county;
	private String street;

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

	public String getBirthyear() {
		return birthyear;
	}

	public void setBirthyear(String birthyear) {
		this.birthyear = birthyear;
	}

	public String getBirthmonth() {
		return birthmonth;
	}

	public void setBirthmonth(String birthmonth) {
		this.birthmonth = birthmonth;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

}
