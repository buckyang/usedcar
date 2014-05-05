package com.amateur.domain;

import java.io.Serializable;

public class ResetPasswordRecord implements Serializable{

	private static final long serialVersionUID = -4241882332560790974L;
	
	private Integer accountId;
	private long expiredDate;
	private String activeCode;
	private String mail;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public long getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(long expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
