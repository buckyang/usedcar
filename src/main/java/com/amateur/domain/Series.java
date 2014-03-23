/**
 * 
 */
package com.amateur.domain;

import java.io.Serializable;
import java.util.List;

public class Series implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5932442639366895444L;
	private Integer seriesId;
	private String series_initial;
	private String series_name;
	private Integer type;
	private String brandId;
	private List<Model> modelList;

	public Integer getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
	}

	public String getSeries_initial() {
		return series_initial;
	}

	public void setSeries_initial(String series_initial) {
		this.series_initial = series_initial;
	}

	public String getSeries_name() {
		return series_name;
	}

	public void setSeries_name(String series_name) {
		this.series_name = series_name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public List<Model> getModelList() {
		return modelList;
	}

	public void setModelList(List<Model> modelList) {
		this.modelList = modelList;
	}

}
