package com.amateur.persistence;

import com.amateur.domain.ProductModel;

public interface ProductModelMapper {

	int insertProductModel(ProductModel productModel);
	int updateProductModel(ProductModel productModel);
	int deleteProductModel(String productId);

}
