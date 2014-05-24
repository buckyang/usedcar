package com.amateur.persistence;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.amateur.domain.Product;

public interface ProductMapper {

	int insertProduct(Product product);

	int updateProdut(Product product);

	int deleteProduct(@Param("productId") String productId,
			@Param("accountId") String accountId);

	Product getProductById(String productId);

	List<HashMap<String, String>> getProductListByAccountId(String accountId);
}
