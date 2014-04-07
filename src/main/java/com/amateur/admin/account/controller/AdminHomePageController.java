package com.amateur.admin.account.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amateur.admin.account.service.AdminPageService;
import com.amateur.controller.AdminBaseController;
import com.amateur.domain.AdminMenu;
import com.amateur.session.AdminProfile;

@Controller
@RequestMapping(value="/admin")
public class AdminHomePageController extends AdminBaseController{

	@Autowired
	private AdminPageService adminPageService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public void form(HttpServletRequest request, Model m) {
		AdminProfile adminProfile = (AdminProfile) request.getSession().getAttribute(AdminProfile.ADMIN_PROFILE_SESSION_KEY);
		List<AdminMenu> menus = adminPageService.loadMenusByProfile(adminProfile);
		m.addAttribute("menus", menus);
	}
		
}