/**
 * 
 */
package com.amateur.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.amateur.account.dto.RegistrationDTO;
import com.amateur.util.EncryptionUtil;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 260686500125121140L;
	private Integer accountId;
	private String password;
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
	private Date updateTime;
	private Date registrationDate;
	private Integer accountType;
	private String profileHash;


	public Account() {
		super();

	}



	public Account(RegistrationDTO registrationDTO, Integer accountId) {
		BeanUtils.copyProperties(registrationDTO, this);
		setRegistrationDate(new Date());
		setPassword(EncryptionUtil.encryptPassword(registrationDTO.getPassword()));
		setAccountId(accountId);
		setProfileHash(EncryptionUtil.getMD5HashValue(accountId.toString()));
	}



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



	public Date getUpdateTime() {
		return updateTime;
	}



	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}



	public Date getRegistrationDate() {
		return registrationDate;
	}



	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}



	public String getProfileHash() {
		return profileHash;
	}



	public void setProfileHash(String profileHash) {
		this.profileHash = profileHash;
	}



	public Integer getAccountType() {
		return accountType;
	}



	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

}
