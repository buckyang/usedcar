package com.amateur.domain;

import java.io.Serializable;
import java.util.List;

public class City implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer cityId;
	private String cityName;
	private List<County> countyList;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<County> getCountyList() {
		return countyList;
	}

	public void setCountyList(List<County> countyList) {
		this.countyList = countyList;
	}

}
