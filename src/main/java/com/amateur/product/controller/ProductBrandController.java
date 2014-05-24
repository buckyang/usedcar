package com.amateur.product.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amateur.service.BrandService;

@Controller
@RequestMapping(value = "/product")
public class ProductBrandController {

	@Autowired
	private BrandService brandService;

	@RequestMapping(value = "/getSeries", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer, String> getSeriesMap(String brandId) {

		return brandService.getSeriesMapByBrandId(Integer.valueOf(brandId));
	}

	@RequestMapping(value = "/getModels", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer, String> getModels(String seriesId) {

		return brandService.getModelMapBySeriesId(Integer.valueOf(seriesId));
	}
}
