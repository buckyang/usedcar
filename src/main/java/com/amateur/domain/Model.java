package com.amateur.domain;

import java.io.Serializable;

public class Model implements Serializable {

	private static final long serialVersionUID = -314678832367015371L;
	private Integer modelId;
	private String modelName;
	private String subSeries;
	private String displacement;
	private String transmissionType;
	private Integer launchYear;
	private String level;
	private String country;

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getSubSeries() {
		return subSeries;
	}

	public void setSubSeries(String subSeries) {
		this.subSeries = subSeries;
	}

	public String getDisplacement() {
		return displacement;
	}

	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}

	public String getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}

	public Integer getLaunchYear() {
		return launchYear;
	}

	public void setLaunchYear(Integer launchYear) {
		this.launchYear = launchYear;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
