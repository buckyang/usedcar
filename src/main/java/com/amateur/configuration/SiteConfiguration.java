package com.amateur.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amateur.domain.SelectOption;
import com.amateur.persistence.SelectOptionMapper;

@Component
public class SiteConfiguration implements InitializingBean{
	public static final String IMAGE_JUMBO_SIZE_KEY = "jumbo_size";
	
	public static final String IMAGE_LARGE_SIZE_KEY = "large_size";
	
	public static final String IMAGE_REGULAR_SIZE_KEY = "regular_size";
	
	public static final String IMAGE_SMALL_SIZE_KEY = "small_size";
	
	public static final String IMAGE_THUMBNAIL_SIZE_KEY = "thumbnail_size";

	private static final String	IMAGE_DIMENSION_SEPARATOR	= ":";
	
	private String resellerTypeName;
	
	private String siteName;
	
	private List<SelectOption> resellerTypeMap;
	@Autowired
	private SelectOptionMapper selectOptionMapper;
	
	private int cookieLoginValidDays;
	
	private int mobileTokenValidDays;
	
	private Map<String, String> imageSizeDimensionMap;
	
	private Map<String, List<Integer>> imageSizeDimension = new HashMap<String, List<Integer>>();
	
	private String productImageContext;
	
	private Map<String,String> imageSizeSuffixMap;
	
	private String staticServerHostNameURL;
	
	private String dynamicServerHostNameURL;

	@Override
	public void afterPropertiesSet() throws Exception {
		if(imageSizeDimensionMap != null){
			for(Entry<String, String> dimensionEntry: imageSizeDimensionMap.entrySet()){
				List<Integer> sizeDimension = new ArrayList<Integer>();
				for(String singleDimension : dimensionEntry.getValue().split(IMAGE_DIMENSION_SEPARATOR)){
					sizeDimension.add(NumberUtils.toInt(singleDimension));
				}
				imageSizeDimension.put(dimensionEntry.getKey(), sizeDimension);
			}
		}
	}
	
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


	public Map<String, String> getImageSizeDimensionMap() {
		return imageSizeDimensionMap;
	}


	public void setImageSizeDimensionMap(Map<String, String> imageSizeDimensionMap) {
		this.imageSizeDimensionMap = imageSizeDimensionMap;
	}

	public Map<String, List<Integer>> getImageSizeDimension() {
		return imageSizeDimension;
	}

	public String getProductImageContext() {
		return productImageContext;
	}

	public void setProductImageContext(String productImageContext) {
		this.productImageContext = productImageContext;
	}

	public Map<String, String> getImageSizeSuffixMap() {
		return imageSizeSuffixMap;
	}

	public void setImageSizeSuffixMap(Map<String, String> imageSizeSuffixMap) {
		this.imageSizeSuffixMap = imageSizeSuffixMap;
	}

	public String getStaticServerHostNameURL() {
		return staticServerHostNameURL;
	}

	public void setStaticServerHostNameURL(String staticServerHostNameURL) {
		this.staticServerHostNameURL = staticServerHostNameURL;
	}

	public String getDynamicServerHostNameURL() {
		return dynamicServerHostNameURL;
	}

	public void setDynamicServerHostNameURL(String dynamicServerHostNameURL) {
		this.dynamicServerHostNameURL = dynamicServerHostNameURL;
	}
	
}
