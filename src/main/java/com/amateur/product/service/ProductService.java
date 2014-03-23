package com.amateur.product.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.amateur.domain.Product;
import com.amateur.domain.ProductImage;
import com.amateur.persistence.ProductAddressMapper;
import com.amateur.persistence.ProductImageMapper;
import com.amateur.persistence.ProductMapper;
import com.amateur.persistence.ProductModelMapper;
import com.amateur.persistence.ProvinceMapper;
import com.amateur.product.dto.UsedCarDTO;
import com.amateur.service.AddressService;
import com.amateur.service.BrandService;
import com.amateur.service.SequenceService;

@Service
public class ProductService {

	private static final Logger logger = Logger.getLogger(ProductService.class);
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProvinceMapper provinceMapper;
	@Autowired
	private ProductAddressMapper productAddressMapper;
	@Autowired
	private ProductImageMapper productImageMapper;
	@Autowired
	private ProductModelMapper productModelMapper;
	@Autowired
	private BrandService brandService;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private AddressService addressService;

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public boolean publishUsedCar(UsedCarDTO usedCarDTO) {
		logger.info("start publishing used car.");
		usedCarDTO.setProductId(sequenceService.getProductId());
//		usedCarDTO.setBrandName(brandService.getBrandNameById(usedCarDTO.getBrandId()));
//		usedCarDTO.setSeriesName(brandService.getSeriesNameById(usedCarDTO.getBrandId(), usedCarDTO.getSeriesId()));
//		usedCarDTO.setModelDisplayName(brandService.getModelDisplayNameById(usedCarDTO.getSeriesId(), usedCarDTO.getModelId()));
		usedCarDTO.setBrandName("大众");
		usedCarDTO.setSeriesName("高尔夫");
		usedCarDTO.setModelDisplayName("大众  高尔夫 2.12 1.4TIS 自动豪华型");
		StringBuilder productName = new StringBuilder();
		productName.append(usedCarDTO.getBrandName());
		productName.append(" ");
		productName.append(usedCarDTO.getSeriesName());
		productName.append(" ");
		productName.append(usedCarDTO.getModelDisplayName());
		usedCarDTO.setProductName(productName.toString());
		usedCarDTO.setStatus(0);
//		usedCarDTO.setProvince(addressService.getProvinceById(usedCarDTO.getProvinceId()));
//		usedCarDTO.setCity(addressService.getCityById(usedCarDTO.getProvinceId(), usedCarDTO.getCityId()));
//		usedCarDTO.setCounty(addressService.getCountyById(usedCarDTO.getCityId(), usedCarDTO.getCountyId()));
		usedCarDTO.setProvince("四川");
		usedCarDTO.setCity("成都");
		usedCarDTO.setCounty("武侯区");
		Product usedCar = new Product();
		usedCar.copyDTO(usedCarDTO);
		Boolean result = true;
		if (productMapper.insertProduct(usedCar) == 1) {
			if (productModelMapper.insertProductModel(usedCar.getProductModel()) == 1) {
				if(productAddressMapper.insertProductAddress(usedCar.getProductAddress()) == 1){
					List<ProductImage> imageList = usedCar.getProductImageList();
					for(ProductImage productImage: imageList){
						if(productImageMapper.insertProdutImage(productImage) == 0){
							result = false;
							break;
						}
					}
				}
			}else{
				result = false;
			}
		}else{
			result = false;
		}
		return result;
	}
	
	public UsedCarDTO getUsedCarById(String productId){
		Product product = productMapper.getProductById(productId);
		return product.getDTO();
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public boolean deleteUsedCar(String productId){
		if(productModelMapper.deleteProductModel(productId)==1){
			if(productImageMapper.deleteProdutImage(productId)==1){
				if(productAddressMapper.deleteProductAddress(productId)==1){
					if(productMapper.deleteProduct(productId)==1){
						return true;
					}
				}
			}
		}
		return false;
	}

}
