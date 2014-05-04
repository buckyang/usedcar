package com.amateur.admin.search.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amateur.controller.AdminBaseController;
import com.amateur.interceptor.ProfileInitializerInterceptor;
import com.amateur.search.SearchManager;
import com.amateur.search.exception.SearchException;

@Controller
@RequestMapping(value = "/admin/search")
public class ScheduleIndexController extends AdminBaseController {
	
	private static final Logger logger = Logger.getLogger(ScheduleIndexController.class);
	@Autowired
	private SearchManager	searchManager;



	@RequestMapping(value = "deleteIndex")
	public String deleteIndex(HttpServletRequest request) {
		boolean success = true;
		try {
			searchManager.removeIndex();
		} catch (SearchException e) {
			logger.error("Delete index failed", e);
		}
		Map<String, Object> tempRedirectAttributes = new HashMap<String, Object>();
		tempRedirectAttributes.put("message", !success ? "admin.search.deleteindex.failsure" :"admin.search.deleteindex.success");
		request.getSession()
				.setAttribute(ProfileInitializerInterceptor.REDIRECT_ATTRIBUTES_KEY, tempRedirectAttributes);
		return "redirect:/admin/search/createIndex";
	}



	@RequestMapping(value = "buildIndex")
	public String buildIndex(HttpServletRequest request) {
		boolean success = true;
		try {
			searchManager.createSampleIndex();
		} catch (SearchException e) {
			logger.error("Create sample index failed", e);
		}		
		Map<String, Object> tempRedirectAttributes = new HashMap<String, Object>();
		tempRedirectAttributes.put("message", success ? "admin.search.createindex.success":"admin.search.createindex.failure");
		request.getSession()
				.setAttribute(ProfileInitializerInterceptor.REDIRECT_ATTRIBUTES_KEY, tempRedirectAttributes);
		return "redirect:/admin/search/createIndex";
	}


	@RequestMapping(value = "testQuery")
	public String testQuery(Model m, HttpServletRequest request) {
		String query = request.getParameter("query");
		QueryResponse queryResponse = null;
		try {
			queryResponse = searchManager.testQuery(query);
		} catch (SearchException e) {
			logger.error("Index error", e);
		}
		m.addAttribute("queryResponse", queryResponse);
		return "redirect:/admin/search/createIndex";
	}

	
	@RequestMapping(value = "createIndex")
	public String get(Model m) {
		return "/admin/search/createIndex";
	}
}
