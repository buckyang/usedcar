package com.amateur.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amateur.service.AddressService;

@Controller
@RequestMapping(value = "/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@RequestMapping(value = "/getProvinces", method = RequestMethod.POST)
	@ResponseBody
	public Map<Integer, String> getProvinceMap() {

		return addressService.getProvinceMap();
	}

	@RequestMapping(value = "/getCities", method = RequestMethod.POST)
	@ResponseBody
	public Map<Integer, String> getCityMap(String provinceId) {

		return addressService.getCityMapByProvinceId(Integer
				.valueOf(provinceId));
	}

	@RequestMapping(value = "/getCounties", method = RequestMethod.POST)
	@ResponseBody
	public Map<Integer, String> getCountyMap(String cityId) {

		return addressService.getCountyMapByCityId(Integer.valueOf(cityId));
	}
}
