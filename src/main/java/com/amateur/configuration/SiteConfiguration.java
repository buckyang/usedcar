package com.amateur.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amateur.domain.SelectOption;
import com.amateur.persistence.SelectOptionMapper;

@Component
public class SiteConfiguration {
	
	private String resellerTypeName;
	
	private List<SelectOption> resellerTypeMap;
	@Autowired
	private SelectOptionMapper selectOptionMapper;
	
	
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

	
	
}