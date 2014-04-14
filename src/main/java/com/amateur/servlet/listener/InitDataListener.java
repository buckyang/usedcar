package com.amateur.servlet.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.amateur.menu.manager.service.MenuManagerService;

public class InitDataListener implements ServletContextListener {

	private ApplicationContext context;

	public InitDataListener() {
		super();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		context = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		MenuManagerService service = (MenuManagerService) context
				.getBean("menuManagerService");
		servletContext.setAttribute("MENU_ITEMS", service.getMenuManagerDTO());
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
