package com.amateur.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MobileRequestFilter implements Filter {

	private static String	HEADER_ACCEPTS		= "Accept";
	private static String	CONTENT_TYPE_JSON	= "application/json";



	public void destroy() {

	}



	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (servletRequest instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
			httpServletRequest.getSession().setMaxInactiveInterval(2);
			JsonHeadersRequest request = new JsonHeadersRequest(httpServletRequest);
			filterChain.doFilter(request, servletResponse);
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}

		return;
	}



	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public class JsonHeadersRequest extends HttpServletRequestWrapper {

		public JsonHeadersRequest(HttpServletRequest request) {
			super(request);
		}



		public String getHeader(String name) {
			HttpServletRequest request = (HttpServletRequest) getRequest();

			if (HEADER_ACCEPTS.equals(name)) {
				return CONTENT_TYPE_JSON;
			}
			return request.getHeader(name);
		}

	}
}