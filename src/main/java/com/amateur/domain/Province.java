package com.amateur.domain;

import java.io.Serializable;
import java.util.List;

public class Province implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer provinceId;
	private String provinceName;
	private List<City> cityList;

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

}
