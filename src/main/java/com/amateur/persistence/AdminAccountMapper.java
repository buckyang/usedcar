package com.amateur.persistence;

import java.util.List;
import java.util.Map;

import com.amateur.domain.AdminAccount;
import com.amateur.domain.AdminMenu;
import com.amateur.domain.AdminPage;

public interface AdminAccountMapper {
		
	AdminAccount selectAccountByLoginName(String name);
	int updateLoginTime(AdminAccount adminAccount);
	List<AdminPage> loadAllPagesWithPermission();
	List<AdminMenu> loadMenusByPermissions(Map<String, String> queryMap);
	List<AdminPage> loadTabGroupByMenuId(Map<String, Object> queryMap);
	List<AdminPage> loadTabGroupByCurrentTab(Map<String, Object> queryMap);
}
