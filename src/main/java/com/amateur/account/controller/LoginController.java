package com.amateur.account.controller;

import javax.servlet.http.Cookie;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.amateur.account.dto.LoginDTO;
import com.amateur.account.service.AccountService;
import com.amateur.account.validator.LoginValidator;
import com.amateur.controller.BaseController;
import com.amateur.domain.Account;
import com.amateur.session.Profile;

@Controller
@SessionAttributes("profile")
public class LoginController extends BaseController{
	@Autowired
	private LoginValidator loginValidator;

	@Autowired
	private AccountService accountService;
	
	@Override
	protected String getPostSuccessCode() {
		return "account.login.success";
	}

	@InitBinder("loginDTO")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(loginValidator);
	}
	
	@ModelAttribute("loginDTO")
	public LoginDTO createFormBean() {
		return new LoginDTO();
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void form() {
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET,  produces="application/json")
	@ResponseBody
	public void formJSON() {
		
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(@ModelAttribute("profile")Profile profile) {
		profile.setAccountDatasource(null);
		profile.setStatus(Profile.ANONYMOUS);
		return "redirect:/login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO,
			BindingResult result,@ModelAttribute("profile")Profile profile, Model m, HttpServletResponse response) {
		if (!result.hasErrors()) {
			handleLogin(loginDTO, profile, m, response);
			return "redirect:/";
		}
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public  PostResultJSON loginJSON(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO,
			BindingResult result,@ModelAttribute("profile")Profile profile, Model m, HttpServletResponse response) {
		if (!result.hasErrors()) {
			handleLogin(loginDTO, profile, m, response);
		}
		return 	processPostJSON(result);
	}	



	private void handleLogin(LoginDTO loginDTO, Profile profile, Model m, HttpServletResponse response) {
		Account account = accountService.getAccountByPhoneOrEmail(loginDTO.getPhoneOrEmail());
		if(account != null){
			profile.setAccountDatasource(account);
			profile.setStatus(Profile.EXPLICIT_LOGIN);
			m.addAttribute("profile", profile);
			Cookie cookie = new Cookie(Profile.COOKIE_USER_ID, "");
			if (loginDTO.getRememberUserName() != null && loginDTO.getRememberUserName()) {
				cookie.setValue(account.getProfileHash());
				cookie.setMaxAge(3600 * 24 * 30 * 12);

			}else{
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
		}
	}
}