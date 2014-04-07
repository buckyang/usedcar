package com.amateur.admin.account.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amateur.admin.account.dto.AdminLoginDTO;
import com.amateur.admin.account.service.AdminAccountService;
import com.amateur.admin.account.validator.AdminLoginValidator;
import com.amateur.controller.AdminBaseController;
import com.amateur.domain.AdminAccount;
import com.amateur.session.AdminProfile;

@Controller
@RequestMapping(value="/admin")
public class AdminLoginController extends AdminBaseController{
	@Autowired
	private AdminLoginValidator adminLoginValidator;

	@Autowired
	private AdminAccountService adminAccountService;

	@InitBinder("adminLoginDTO")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(adminLoginValidator);
	}
	
	@ModelAttribute("adminLoginDTO")
	public AdminLoginDTO createFormBean() {
		return new AdminLoginDTO();
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void form() {
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		AdminProfile adminProfile = (AdminProfile) request.getSession().getAttribute(AdminProfile.ADMIN_PROFILE_SESSION_KEY);
		adminProfile.setAccountDatasource(null);
		request.getSession().invalidate();
		return "redirect:login";
	}
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("adminLoginDTO") AdminLoginDTO adminLoginDTO,
			BindingResult result, Model m, HttpServletRequest request, HttpServletResponse response) {
		if (!result.hasErrors()) {
			AdminAccount adminAccount = adminAccountService.selectAccountByLoginName(adminLoginDTO.getLoginName().trim());
			adminAccount.setLastLoginTime(new Date());
			adminAccountService.updateLoginTime(adminAccount);
			AdminProfile adminProfile = (AdminProfile) request.getSession().getAttribute(AdminProfile.ADMIN_PROFILE_SESSION_KEY);
			adminProfile.setAccountDatasource(adminAccount);
			adminProfile.setStatus(AdminProfile.EXPLICIT_LOGIN);
			return "redirect:/admin/index";
		}
		return "/admin/login";
	}
	
}