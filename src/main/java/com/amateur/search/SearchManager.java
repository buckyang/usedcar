package com.amateur.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amateur.search.configuration.SearchConfiguration;
import com.amateur.search.exception.SearchException;
import com.amateur.search.pojo.ProductPOJO;

@Component
public class SearchManager implements InitializingBean {

	private static final Logger	logger						= Logger.getLogger(SearchManager.class);

	private static final String	REMOVE_INDEX_QUERY_STRING	= "*:*";

	private SolrServer			searchServer;
	@Autowired
	private SearchConfiguration	searchConfiguration;



	public void removeIndex() throws SearchException{
		try {
			long beforeTime = System.currentTimeMillis();
			searchServer.deleteByQuery(REMOVE_INDEX_QUERY_STRING);
			searchServer.commit();
			logger.info("Remove index time " + (System.currentTimeMillis() - beforeTime) + "ms");
		} catch (SolrServerException e) {
			throw new SearchException("Remove index failed",e);
		} catch (IOException e) {
			throw new SearchException("Remove index failed",e);
		}
	}



	public void createSampleIndex() throws SearchException {
		try {
			long beforeTime = System.currentTimeMillis();
			ProductPOJO product = new ProductPOJO();
			product.setProductId("10001");
			product.setProductName("全新丰田二手车");
			product.setBrandName("TOYOTA丰田");
			product.setCarVin("XTA210900N1093188");
			product.setListPrice(30000.00);
			product.setModelName("");
			product.setSeriesName("corola卡罗拉");
			product.setDisplacement("1.6L");
			product.setOdometer(8000);
			product.setPriceType(1);
			product.setTransmissionType("自动挡");
			product.setPurchaseDate(DateUtils.parseDate("2010-07-01", "yyyy-MM-dd"));
			product.setUpdateTime(new Date());

			ProductPOJO product1 = new ProductPOJO();
			product1.setProductId("10002");
			product1.setProductName("超值本田二手车");
			product1.setBrandName("HONDA本田");
			product1.setCarVin("WBAHL83558DT89753");
			product1.setListPrice(80000.00);
			product1.setModelName("");
			product1.setSeriesName("civic思域");
			product1.setDisplacement("1.6L");
			product1.setOdometer(16000);
			product1.setPriceType(1);
			product1.setTransmissionType("自动挡");
			product1.setPurchaseDate(DateUtils.parseDate("2010-07-01", "yyyy-MM-dd"));
			product1.setUpdateTime(new Date());

			List<ProductPOJO> items = new ArrayList<ProductPOJO>();
			items.add(product);
			items.add(product1);
			UpdateResponse response = searchServer.addBeans(items);
			searchServer.commit();
			logger.info("Index time cost" + (System.currentTimeMillis() - beforeTime) + "ms");
		} catch (Exception e) {
			throw new SearchException("Create index failed",e);
		}
	}



	public QueryResponse testQuery(String queryString) throws SearchException {
		SolrQuery solrQuery = new SolrQuery().setQuery(queryString).setFacet(true).setFacetMinCount(1).setFacetLimit(8)
				.addFacetField("brandName").addFacetField("priceType");
		QueryResponse rsp = null;
		try {
			rsp = searchServer.query(solrQuery);
		} catch (SolrServerException e) {
			throw new SearchException("Search failed",e);
		}
		return rsp;
	}



	@Override
	public void afterPropertiesSet() throws Exception {
		searchServer = new HttpSolrServer(searchConfiguration.getServerURL());
	}

}
