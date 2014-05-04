package com.amateur.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amateur.domain.City;
import com.amateur.domain.County;
import com.amateur.domain.Province;
import com.amateur.persistence.ProvinceMapper;

@Service
public class AddressService {

	@Autowired
	private ProvinceMapper provinceMapper;
	private Map<Integer, String> provinceMap = new LinkedHashMap<Integer, String>();
	private Map<Integer, Map<Integer, String>> cityMap = new LinkedHashMap<Integer, Map<Integer, String>>();
	private Map<Integer, Map<Integer, String>> countyMap = new LinkedHashMap<Integer, Map<Integer, String>>();
	
	public Map<Integer, String> getProvinceMap(){
		if(provinceMap.size()==0){
			initializeAddressData();
		}
		return this.provinceMap;
	}
	
	public Map<Integer, String> getCityMapByProvinceId(Integer provinceId){
		if(cityMap.size()==0){
			initializeAddressData();
		}
		return cityMap.get(provinceId);
	}
	
	public Map<Integer, String> getCountyMapByCityId(Integer cityId){
		if(countyMap.size()==0){
			initializeAddressData();
		}
		return countyMap.get(cityId);
	}
	
	public String getProvinceById(Integer provinceId) {
		if(provinceMap.size()==0){
			initializeAddressData();
		}
		return provinceMap.get(provinceId);
	}

	public String getCityById(Integer provinceId, Integer cityId) {
		if(cityMap.size()==0){
			initializeAddressData();
		}
		return cityMap.get(provinceId).get(cityId);
	}

	public String getCountyById(Integer cityId, Integer countyId) {
		if(countyMap.size()==0){
			initializeAddressData();
		}
		return countyMap.get(cityId).get(countyId);
	}

	public void initializeAddressData() {
		List<Province> provinceList = provinceMapper.getAllProvinces();
		for (Province province : provinceList) {
			provinceMap.put(province.getProvinceId(),
					province.getProvinceName());
			List<City> cityList = province.getCityList();
			Map<Integer, String> cityTempMap = new LinkedHashMap<Integer, String>();
			for (City city : cityList) {
				cityTempMap.put(city.getCityId(), city.getCityName());
				List<County> countyList = city.getCountyList();
				Map<Integer, String> countyTempMap = new LinkedHashMap<Integer, String>();
				for (County county : countyList) {
					countyTempMap.put(county.getCountyId(),
							county.getCountyName());
				}
				countyMap.put(city.getCityId(), countyTempMap);
			}
			cityMap.put(province.getProvinceId(), cityTempMap);
		}
	}
}
