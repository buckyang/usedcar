package com.amateur.interceptor;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.amateur.controller.BaseController;
import com.amateur.servlet.ServletUtil;
import com.amateur.session.Profile;

@Component
public class MobileAuthorizationInteceptor extends HandlerInterceptorAdapter {
	static Logger		logger	= Logger.getLogger(MobileAuthorizationInteceptor.class);

	private String		accountAccessDenyURL;
	private String[]	ingorePaths;

	@Autowired
	protected MessageSource		messageSource;

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
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> deniedResponse = new LinkedHashMap<String, Object>();
			deniedResponse.put(BaseController.EXECUTION_RESULT_PARAM_KEY, false);
			String message = null;
			try {
				message = messageSource.getMessage("mobile.accesscontrol.login", null, null);
			} catch (NoSuchMessageException e) {
			}
			deniedResponse.put(BaseController.MESSAGE_PARAM_KEY, message);
			String deniedJSONString = objectMapper.writeValueAsString(deniedResponse);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().println(deniedJSONString);
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
