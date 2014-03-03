package com.amateur.domain;

import java.io.Serializable;

public class SelectOption implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3239062997035559570L;
	private Integer id;
	private String optionValue;
	private String optionDisplayName;
	private Integer displayOrder;
	private Boolean defaultSelected;
	private String selectionName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	public String getOptionDisplayName() {
		return optionDisplayName;
	}
	public void setOptionDisplayName(String optionDisplayName) {
		this.optionDisplayName = optionDisplayName;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public Boolean getDefaultSelected() {
		return defaultSelected;
	}
	public void setDefaultSelected(Boolean defaultSelected) {
		this.defaultSelected = defaultSelected;
	}
	public String getSelectionName() {
		return selectionName;
	}
	public void setSelectionName(String selectionName) {
		this.selectionName = selectionName;
	}
	
}
