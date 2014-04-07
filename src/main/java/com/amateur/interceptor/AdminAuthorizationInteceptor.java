package com.amateur.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.amateur.admin.account.service.AdminPageService;
import com.amateur.domain.AdminPage;
import com.amateur.servlet.ServletUtil;
import com.amateur.session.AdminProfile;

@Component
public class AdminAuthorizationInteceptor extends HandlerInterceptorAdapter {
	private static final String	PAGE_PARAM_TAB_GROUP	= "tabGroup";

	static Logger		logger	= Logger.getLogger(AdminAuthorizationInteceptor.class);

	private String		accountAccessDenyURL;
	private String[]	ingorePaths;
	@Autowired
	private AdminPageService adminPageService;



	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug("Intercepted request: " + request.getRequestURI());
		if (getIngorePaths() != null) {
			for (String path : getIngorePaths()) {
				if (StringUtils.startsWith(ServletUtil.getRequestURIWithoutContext(request), path)) {
					return true;
				}
			}
		}

		HttpSession session = request.getSession();
		AdminProfile adminProfile = (AdminProfile) session.getAttribute(AdminProfile.ADMIN_PROFILE_SESSION_KEY);
		String currentPage = ServletUtil.getRequestURIWithoutContext(request);
		if (adminProfile.getStatus() == AdminProfile.ANONYMOUS || !adminPageService.allowAccess(currentPage, adminProfile)) {
			response.sendRedirect(ServletUtil.appendContextPath(request, getAccountAccessDenyURL())
					+ ServletUtil.getRequestFileExtension(request));
			Map<String, Object> tempRedirectAttributes = new HashMap<String, Object>();
			tempRedirectAttributes.put("message", "admin.accesscontrol.login");
			session.setAttribute(ProfileInitializerInterceptor.REDIRECT_ATTRIBUTES_KEY, tempRedirectAttributes);

			return false;
		}
		loadTabGroup(request, adminProfile);
		return true;
	}
	private void loadTabGroup(HttpServletRequest request, AdminProfile adminProfile){
		int menuId = NumberUtils.toInt(request.getParameter("menuId"), -1);
		List<AdminPage> tabGroup = null;
		if(menuId != -1){
			tabGroup = adminPageService.loadTabGroupByMenuId(adminProfile, menuId);
		}else{
			tabGroup = adminPageService.loadTabGroupByCurrentTab(adminProfile, ServletUtil.getRequestURIWithoutContext(request));
		}
		if(CollectionUtils.isNotEmpty(tabGroup)){
			request.setAttribute(PAGE_PARAM_TAB_GROUP, tabGroup);
		}

	}


	public String getAccountAccessDenyURL() {
		return accountAccessDenyURL;
	}



	public void setAccountAccessDenyURL(String accountAccessDenyURL) {
		this.accountAccessDenyURL = accountAccessDenyURL;
	}



	public String[] getIngorePaths() {
		return ingorePaths;
	}



	public void setIngorePaths(String[] ingorePaths) {
		this.ingorePaths = ingorePaths;
	}

}
