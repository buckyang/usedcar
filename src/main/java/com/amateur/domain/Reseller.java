package com.amateur.domain;

import com.amateur.account.dto.RegistrationDTO;

public class Reseller extends Account {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2875768362174702166L;
	private Integer	resellerType;
	private String	resellerName;

	public Reseller() {
		super();
	}

	public Reseller(RegistrationDTO registrationDTO, Integer accountId) {
		super(registrationDTO, accountId);
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

}
