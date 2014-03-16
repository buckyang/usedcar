package com.amateur.account.dto;

import java.io.Serializable;

public class UpdatePasswordDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String oldPWD;
	private String newPWD;
	private String confirmPWD;
	private Integer accountId;

	public String getOldPWD() {
		return oldPWD;
	}

	public void setOldPWD(String oldPWD) {
		this.oldPWD = oldPWD;
	}

	public String getNewPWD() {
		return newPWD;
	}

	public void setNewPWD(String newPWD) {
		this.newPWD = newPWD;
	}

	public String getConfirmPWD() {
		return confirmPWD;
	}

	public void setConfirmPWD(String confirmPWD) {
		this.confirmPWD = confirmPWD;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

}
