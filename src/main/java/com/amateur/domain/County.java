package com.amateur.domain;

import java.io.Serializable;

public class County implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer countyId;
	private String countyName;

	public Integer getCountyId() {
		return countyId;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

}
