package com.amateur.persistence;

import java.util.HashMap;
import java.util.List;

import com.amateur.domain.Product;

public interface ProductMapper {
		
	int insertProduct(Product product);
	int updateProdut(Product product);
	int deleteProduct(String productId);
	Product getProductById(String productId);	
	List<HashMap<String, String>> getProductListByAccountId(Integer productId);
}
