package com.amateur.service;

import java.util.LinkedHashMap;
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
	private Map<Integer, String> brandMap = new LinkedHashMap<Integer, String>();
	private Map<Integer, Map<Integer, String>> seriesMap = new LinkedHashMap<Integer, Map<Integer, String>>();
	private Map<Integer, Map<Integer, String>> modelMap = new LinkedHashMap<Integer, Map<Integer, String>>();

	public Map<Integer, String> getBrandMap() {
		if (brandMap.size() == 0) {
			initialBrandData();
		}
		return this.brandMap;
	}

	public Map<Integer, String> getSeriesMapByBrandId(Integer brandId) {
		if (seriesMap.size() == 0) {
			initialBrandData();
		}
		return seriesMap.get(brandId);
	}

	public Map<Integer, String> getModelMapBySeriesId(Integer seriesId) {
		if (modelMap.size() == 0) {
			initialBrandData();
		}
		return modelMap.get(seriesId);
	}

	public String getBrandNameById(Integer brandId) {
		if (brandMap.size() == 0) {
			initialBrandData();
		}
		return brandMap.get(brandId);
	}

	public String getSeriesNameById(Integer brandId, Integer seriesId) {
		if (seriesMap.size() == 0) {
			initialBrandData();
		}
		return seriesMap.get(brandId).get(seriesId);
	}

	public String getModelDisplayNameById(Integer seriesId, Integer modelId) {
		if (modelMap.size() == 0) {
			initialBrandData();
		}
		return modelMap.get(seriesId).get(modelId);
	}

	public void initialBrandData() {
		List<Brand> brandList = brandMapper.getAllBrands();
		for (Brand brand : brandList) {
			brandMap.put(brand.getBrandId(), brand.getBrandInitial() + " "
					+ brand.getBrandName());
			List<Series> seriesList = brand.getSeriesList();
			Map<Integer, String> seriesTempMap = new LinkedHashMap<Integer, String>();
			for (Series series : seriesList) {
				seriesTempMap.put(series.getSeriesId(), series.getSeriesName());
				List<Model> modelList = series.getModelList();
				Map<Integer, String> modelTempMap = new LinkedHashMap<Integer, String>();
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
