/**
 * 
 */
package com.amateur.domain;

import java.io.Serializable;
import java.util.List;

public class Brand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8435669008542298256L;
	private Integer brandId;
	private String brandInitial;
	private String brandName;
	private String brandDescription;
	private List<Series> seriesList;

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandInitial() {
		return brandInitial;
	}

	public void setBrandInitial(String brandInitial) {
		this.brandInitial = brandInitial;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandDescription() {
		return brandDescription;
	}

	public void setBrandDescription(String brandDescription) {
		this.brandDescription = brandDescription;
	}

	public List<Series> getSeriesList() {
		return seriesList;
	}

	public void setSeriesList(List<Series> seriesList) {
		this.seriesList = seriesList;
	}

}
