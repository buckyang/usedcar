package com.amateur.servlet;

import javax.servlet.http.HttpServletRequest;

public class ServletUtil {
	public static final String	PATH_EXTENSION_SEPARATOR	= ".";



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
}
