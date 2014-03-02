package com.amateur.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.amateur.servlet.ServletUtil;
import com.amateur.session.Profile;

@Component
public class UserAuthorizationInteceptor extends HandlerInterceptorAdapter {
	static Logger		logger	= Logger.getLogger(UserAuthorizationInteceptor.class);

	private String		accountAccessDenyURL;
	private String[]	ingorePaths;



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
		Profile profile = (Profile) session.getAttribute("profile");
		if (profile.getStatus() == Profile.ANONYMOUS) {
			response.sendRedirect(ServletUtil.appendContextPath(request, getAccountAccessDenyURL())
					+ ServletUtil.getRequestFileExtension(request));
			Map<String, Object> tempRedirectAttributes = new HashMap<String, Object>();
			tempRedirectAttributes.put("message", "accesscontrol.login");
			session.setAttribute(ProfileInitializerInterceptor.REDIRECT_ATTRIBUTES_KEY, tempRedirectAttributes);

			return false;
		}

		return super.preHandle(request, response, handler);
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
