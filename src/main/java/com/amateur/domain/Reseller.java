package com.amateur.domain;

public class Reseller extends Account {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2875768362174702166L;
	private Integer	resellerType;
	private String	resellerName;



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
