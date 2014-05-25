package com.amateur.domain;

import java.io.Serializable;
import java.util.Date;

public class VerificationCode implements Serializable {

	private static final long serialVersionUID = -4241882332560790974L;

	private Integer id;

	private Integer type;

	private String verificationCode;

	private Date validDate;

	private boolean used;

	private String principle;

	private Date usedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public String getPrinciple() {
		return principle;
	}

	public void setPrinciple(String principle) {
		this.principle = principle;
	}

	public Date getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}

}
