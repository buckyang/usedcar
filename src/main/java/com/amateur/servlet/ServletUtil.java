package com.amateur.servlet;

import javax.servlet.http.HttpServletRequest;

import com.amateur.domain.MobileToken;

public class ServletUtil {
	public static final String	PATH_EXTENSION_SEPARATOR	= ".";
	public static final String HEADER_USER_AGENT = "User-Agent";
	public static final String HEADER_USER_AGENT_DEFAULT = "Empty";
	public static final String	MOBILE_DEVICE_ID	= "deviceId";


	public static String getRequestURIWithoutContext(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		if (contextPath.length() <= 1) {
			return requestURI;
		} else {
			return requestURI.substring(request.getContextPath().length());
		}
	}



	public static String appendContextPath(HttpServletRequest request, String path) {
		String contextPath = request.getContextPath();
		if (contextPath.length() <= 1) {
			return path;
		} else {
			return contextPath + path;
		}
	}



	public static String getRequestFileExtension(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		if (requestURI.lastIndexOf(PATH_EXTENSION_SEPARATOR) > 0) {
			return requestURI.substring(requestURI.lastIndexOf(PATH_EXTENSION_SEPARATOR));
		} else {
			return "";
		}
	}
	


	public static String getUserAgent(HttpServletRequest request) {
		String userAgent = request.getHeader(HEADER_USER_AGENT);
		return userAgent == null ? HEADER_USER_AGENT_DEFAULT : userAgent;
	}



	public static String getMobileDeviceId(HttpServletRequest request) {
		String deviceId = request.getParameter(MOBILE_DEVICE_ID);
		return deviceId == null ? MobileToken.DEFAULT_MOBILE_IDENTIFIER : deviceId;
	}
}
