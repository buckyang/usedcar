package com.amateur.interceptor;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.amateur.account.service.AccountService;
import com.amateur.configuration.SiteConfiguration;
import com.amateur.domain.Account;
import com.amateur.session.Profile;

public class ProfileInitializerInterceptor extends HandlerInterceptorAdapter {
	private static final String	SITE_CONFIGURATION		= "siteConfiguration";
	private static Logger		logger					= Logger.getLogger(ProfileInitializerInterceptor.class);
	public static final String	REDIRECT_ATTRIBUTES_KEY	= "tempRedirectAttributes";

	private SiteConfiguration	siteConfiguration;
	private AccountService		accountService;



	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession();
		if (session.getAttribute(Profile.PROFILE_KEY) == null) {
			logger.debug("Initialize profile from[ address: " + request.getRemoteAddr() + ", "
					+ request.getSession().getId() + "]");
			Profile profile = processCookieLogin(request, response);
			if (profile == null) {
				profile = new Profile();
			}
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



	private Profile processCookieLogin(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (Profile.COOKIE_USER_ID.equals(cookie.getName()) && StringUtils.isNotBlank(cookie.getValue())) {
					Account account = accountService.getAccountByProfileHash(cookie.getValue());
					Profile profile = new Profile();
					profile.setAccountDatasource(account);
					profile.setStatus(Profile.COOKIE_LOGIN);
					return profile;
				}
			}
		}
		return null;
	}



	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute(SITE_CONFIGURATION, getSiteConfiguration());
		super.postHandle(request, response, handler, modelAndView);
	}



	public SiteConfiguration getSiteConfiguration() {
		return siteConfiguration;
	}



	public void setSiteConfiguration(SiteConfiguration siteConfiguration) {
		this.siteConfiguration = siteConfiguration;
	}



	public AccountService getAccountService() {
		return accountService;
	}



	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}
