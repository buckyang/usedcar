package com.amateur.interceptor;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.amateur.session.Profile;

public class ProfileInitializerInterceptor extends HandlerInterceptorAdapter {
	private static Logger		logger					= Logger.getLogger(ProfileInitializerInterceptor.class);
	public static final String	REDIRECT_ATTRIBUTES_KEY	= "tempRedirectAttributes";



	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession();
		if (session.getAttribute(Profile.PROFILE_KEY) == null) {
			logger.debug("Initialize profile from[ address: " + request.getRemoteAddr() + ", "
					+ request.getSession().getId() + "]");
			Profile profile = new Profile();
			session.setAttribute(Profile.PROFILE_KEY, profile);
		}
		if (session.getAttribute(REDIRECT_ATTRIBUTES_KEY) != null) {
			Map<String, Object> tempRedirectAttributes = (Map<String, Object>) session
					.getAttribute(REDIRECT_ATTRIBUTES_KEY);
			for (Entry<String, Object> entry : tempRedirectAttributes.entrySet()) {
				request.setAttribute(entry.getKey(), entry.getValue());
			}
			session.removeAttribute(REDIRECT_ATTRIBUTES_KEY);
		}

		return super.preHandle(request, response, handler);
	}

}
