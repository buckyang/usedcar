package com.amateur.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amateur.domain.Brand;
import com.amateur.domain.Model;
import com.amateur.domain.Series;
import com.amateur.persistence.BrandMapper;

@Service
public class BrandService {
	@Autowired
	private BrandMapper brandMapper;
	private Map<Integer, String> brandMap;
	private Map<Integer, Map<Integer, String>> seriesMap;
	private Map<Integer, Map<Integer, String>> modelMap;

	public String getBrandNameById(Integer brandId) {
		return brandMap.get(brandId);
	}

	public String getSeriesNameById(Integer brandId, Integer seriesId) {
		return seriesMap.get(brandId).get(seriesId);
	}

	public String getModelDisplayNameById(Integer seriesId, Integer modelId) {
		return modelMap.get(seriesId).get(modelId);
	}

	public void initialBrandData() {
		List<Brand> brandList = brandMapper.getAllBrands();
		brandMap = new HashMap<Integer, String>();
		seriesMap = new HashMap<Integer, Map<Integer, String>>();
		modelMap = new HashMap<Integer, Map<Integer, String>>();
		for (Brand brand : brandList) {
			brandMap.put(brand.getBrandId(), brand.getBrandInitial() + " "
					+ brand.getBrandName());
			List<Series> seriesList = brand.getSeriesList();
			Map<Integer, String> seriesTempMap = new HashMap<Integer, String>();
			for (Series series : seriesList) {
				seriesTempMap.put(
						series.getSeriesId(),
						series.getSeries_initial() + " "
								+ series.getSeries_name());
				List<Model> modelList = series.getModelList();
				Map<Integer, String> modelTempMap = new HashMap<Integer, String>();
				for (Model model : modelList) {
					modelTempMap.put(model.getModelId(), model.getSubSeries()
							+ " " + model.getModelName());
				}
				modelMap.put(series.getSeriesId(), modelTempMap);
			}
			seriesMap.put(brand.getBrandId(), seriesTempMap);
		}
	}
}
