package com.amateur.account.controller;

import java.util.HashMap;
import java.util.Map;

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

import com.amateur.account.dto.RegistrationDTO;
import com.amateur.account.service.AccountService;
import com.amateur.account.validator.RegistrationValidator;
import com.amateur.configuration.SiteConfiguration;
import com.amateur.controller.BaseController;
import com.amateur.session.Profile;

@Controller
@SessionAttributes("profile")
public class RegistrationController extends BaseController {
	@Autowired
	private AccountService			accountService;
	@Autowired
	private RegistrationValidator	registrationValidator;
	@Autowired
	private SiteConfiguration siteConfiguration;
	



	@Override
	protected String getPostSuccessCode() {
		return "account.registration.success";
	}



	@InitBinder("registrationDTO")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(registrationValidator);
	}



	@ModelAttribute("registrationDTO")
	public RegistrationDTO createFormBean() {
		return new RegistrationDTO();
	}
	/**
	 * Pre-loading form backing objects.
	 * @return
	 */
	@ModelAttribute("preLoadBeans")
	public Map<String,Object> preLoadBeans(){
		Map<String,Object> preLoadBeanMap = new HashMap<String,Object>();
		preLoadBeanMap.put("resellerTypes", siteConfiguration.getResellerTypeList());
		return preLoadBeanMap;
	}


	@RequestMapping(value = "/signon", method = RequestMethod.GET)
	public void form() {
	}



	@RequestMapping(value = "/signon", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String,Object> formJSON() {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("resellerTypes", siteConfiguration.getResellerTypeList());
		return returnMap;
	}



	@RequestMapping(value = "/signon", method = RequestMethod.POST)
	public void registerAccount(@Valid @ModelAttribute("registrationDTO") RegistrationDTO registrationDTO,
			BindingResult result, @ModelAttribute("profile") Profile profile, Model m) {
		if (!result.hasErrors()) {
			handleRegistration(registrationDTO, profile);
			m.addAttribute("message", getPostSuccessCode());
		}
	}



	@RequestMapping(value = "/signon", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public PostResultJSON registerAccountJSON(@Valid @ModelAttribute("registrationDTO") RegistrationDTO registrationDTO,
			BindingResult result, @ModelAttribute("profile") Profile profile, Model m) {
		if (!result.hasErrors()) {
			handleRegistration(registrationDTO, profile);
		}
		return processPostJSON(result);
	}



	private void handleRegistration(RegistrationDTO registrationDTO, Profile profile) {
		if (accountService.registrerAccount(registrationDTO)) {
			profile.setAccountDatasource(accountService.getAccountByEmail(registrationDTO.getEmail()));
			profile.setStatus(Profile.EXPLICIT_LOGIN);
		}
	}

}
