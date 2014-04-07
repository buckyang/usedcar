package com.amateur.admin.account.service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amateur.domain.AdminMenu;
import com.amateur.domain.AdminPage;
import com.amateur.domain.AdminPermission;
import com.amateur.persistence.AdminAccountMapper;
import com.amateur.session.AdminProfile;

@Service
public class AdminPageService {
	private int							pageCacheTimeInHour	= 1;
	private Date						pageExpirationTime;
	private Map<String, Set<String>>	pageWithPermissionMap;
	@Autowired
	private AdminAccountMapper			adminAccountMapper;



	public boolean allowAccess(String page, AdminProfile adminProfile) {
		if (pageWithPermissionMap == null) {
			pageWithPermissionMap = loadPagePermissionMap();
		} else {
			if (pageExpirationTime == null || pageExpirationTime.compareTo(new Date()) < 0) {
				pageWithPermissionMap = loadPagePermissionMap();
				pageExpirationTime = DateUtils.addHours(new Date(), pageCacheTimeInHour);
			}
		}
		Set<String> pagePermissions = pageWithPermissionMap.get(page);
		if (CollectionUtils.isNotEmpty(pagePermissions)) {
			for (String profilePermission : adminProfile.getPermissions()) {
				if (pagePermissions.contains(profilePermission)) {
					return true;
				}
			}
			return false;
		}
		return true;
	}

	

	private Map<String, Set<String>> loadPagePermissionMap() {
		List<AdminPage> allPagesWithPermission = adminAccountMapper.loadAllPagesWithPermission();
		if (allPagesWithPermission == null) {
			Collections.emptyMap();
		}
		Map<String, Set<String>> pagePermissionMap = new HashMap<String, Set<String>>();
		for (AdminPage page : allPagesWithPermission) {
			Set<String> permissions = new HashSet<String>();
			for (AdminPermission permission : page.getPermissions()) {
				permissions.add(permission.getPermission());
			}
			pagePermissionMap.put(page.getUrl(), permissions);
		}
		return pagePermissionMap;
	}
	
	public List<AdminMenu> loadMenusByProfile(AdminProfile adminProfile){
		List<String> adminPermissions = adminProfile.getPermissions();
		if(CollectionUtils.isNotEmpty(adminPermissions)){
			StringBuilder sb = new StringBuilder();
			for(String permission: adminPermissions){
				sb.append("\"").append(permission).append("\",");
			}
			String permissionsStr = sb.substring(0, sb.length() - 1);
			Map<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("permissionsStr", permissionsStr);
			return adminAccountMapper.loadMenusByPermissions(queryMap);
		}
		return Collections.emptyList();
	}

	public List<AdminPage> loadTabGroupByMenuId(AdminProfile adminProfile, int menuId){
		List<String> adminPermissions = adminProfile.getPermissions();
		if(CollectionUtils.isNotEmpty(adminPermissions)){
			StringBuilder sb = new StringBuilder();
			for(String permission: adminPermissions){
				sb.append("\"").append(permission).append("\",");
			}
			String permissionsStr = sb.substring(0, sb.length() - 1);
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("menuId", menuId);
			queryMap.put("permissionsStr", permissionsStr);
			return adminAccountMapper.loadTabGroupByMenuId(queryMap);
		}
		return Collections.emptyList();
	}
	public List<AdminPage> loadTabGroupByCurrentTab(AdminProfile adminProfile, String currentTabURL){
		List<String> adminPermissions = adminProfile.getPermissions();
		if(CollectionUtils.isNotEmpty(adminPermissions)){
			StringBuilder sb = new StringBuilder();
			for(String permission: adminPermissions){
				sb.append("\"").append(permission).append("\",");
			}
			String permissionsStr = sb.substring(0, sb.length() - 1);
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("currentTabURL", currentTabURL);
			queryMap.put("permissionsStr", permissionsStr);
			return adminAccountMapper.loadTabGroupByCurrentTab(queryMap);
		}
		return Collections.emptyList();
	}

}
