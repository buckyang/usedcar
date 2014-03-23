/**
 * 
 */
package com.amateur.domain;

import java.io.Serializable;

public class Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -314678832367015371L;
	private Integer modelId;
	private String modelName;
	private String subSeries;
	private String displacement;
	private int transmissionType;
	private int launchYear;
	private int drivenType;
	private int seriesId;

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

	public int getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(int transmissionType) {
		this.transmissionType = transmissionType;
	}

	public int getLaunchYear() {
		return launchYear;
	}

	public void setLaunchYear(int launchYear) {
		this.launchYear = launchYear;
	}

	public int getDrivenType() {
		return drivenType;
	}

	public void setDrivenType(int drivenType) {
		this.drivenType = drivenType;
	}

	public int getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}

}
