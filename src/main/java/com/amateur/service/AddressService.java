package com.amateur.service;

import java.util.HashMap;
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
	private Map<Integer, String> provinceMap;
	private Map<Integer, Map<Integer, String>> cityMap;
	private Map<Integer, Map<Integer, String>> countyMap;

	public String getProvinceById(Integer provinceId) {
		return provinceMap.get(provinceId);
	}

	public String getCityById(Integer provinceId, Integer cityId) {
		return cityMap.get(provinceId).get(cityId);
	}

	public String getCountyById(Integer cityId, Integer countyId) {
		return countyMap.get(cityId).get(countyId);
	}

	public void initialBrandData() {
		List<Province> provinceList = provinceMapper.getAllProvinces();
		provinceMap = new HashMap<Integer, String>();
		for (Province province : provinceList) {
			provinceMap.put(province.getProvinceId(),
					province.getProvinceName());
			List<City> cityList = province.getCityList();
			Map<Integer, String> cityTempMap = new HashMap<Integer, String>();
			for (City city : cityList) {
				cityTempMap.put(city.getCityId(), city.getCityName());
				List<County> countyList = city.getCountyList();
				Map<Integer, String> countyTempMap = new HashMap<Integer, String>();
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
