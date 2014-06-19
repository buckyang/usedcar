package com.amateur.product.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.amateur.service.BrandService;

@Controller
@RequestMapping(value = "/product")
public class ProductBrandController {

	@Autowired
	private BrandService brandService;

	@RequestMapping(value = "/getBrands", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer, String> getBrandMap() {
		
		return brandService.getBrandMap();
	}
	
	@RequestMapping(value = "/getSeries", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer, String> getSeriesMap(@RequestParam String brandId) {

		return brandService.getSeriesMapByBrandId(Integer.valueOf(brandId));
	}

	@RequestMapping(value = "/getModels", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer, String> getModelMap(@RequestParam String seriesId) {

		return brandService.getModelMapBySeriesId(Integer.valueOf(seriesId));
	}
}
