package com.amateur.account.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RestPasswordDTO implements Serializable {

	private static final long serialVersionUID = 1668155526141555779L;

	private String principle;

	private String activeCode;

	@NotNull
	@Size(min = 6)
	private String newPassword;

	@NotNull
	@Size(min = 6)
	private String confirmPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public String getPrinciple() {
		return principle;
	}

	public void setPrinciple(String principle) {
		this.principle = principle;
	}

}
