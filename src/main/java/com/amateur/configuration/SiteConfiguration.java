package com.amateur.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amateur.domain.SelectOption;
import com.amateur.persistence.SelectOptionMapper;

@Component
public class SiteConfiguration {
	
	private String resellerTypeName;
	
	private String siteName;
	
	private List<SelectOption> resellerTypeMap;
	@Autowired
	private SelectOptionMapper selectOptionMapper;
	
	private int cookieLoginValidDays;
	
	private int mobileTokenValidDays;
	
	
	public List<SelectOption> getResellerTypeList() {
		if(resellerTypeMap == null){
			return selectOptionMapper.getSelectionOptionsByName(getResellerTypeName());
		}
		return resellerTypeMap;
	}


	public String getResellerTypeName() {
		return resellerTypeName;
	}


	public void setResellerTypeName(String resellerTypeName) {
		this.resellerTypeName = resellerTypeName;
	}


	public String getSiteName() {
		return siteName;
	}


	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}


	public int getCookieLoginValidDays() {
		return cookieLoginValidDays;
	}


	public void setCookieLoginValidDays(int cookieLoginValidDays) {
		this.cookieLoginValidDays = cookieLoginValidDays;
	}


	public int getMobileTokenValidDays() {
		return mobileTokenValidDays;
	}


	public void setMobileTokenValidDays(int mobileTokenValidDays) {
		this.mobileTokenValidDays = mobileTokenValidDays;
	}

	
	
}
