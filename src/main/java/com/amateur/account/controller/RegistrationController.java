package com.amateur.account.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
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
import com.amateur.domain.MobileToken;
import com.amateur.servlet.ServletUtil;
import com.amateur.session.Profile;
import com.amateur.util.EncryptionUtil;

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


	@RequestMapping(value = {"/signon", "/resellerSignon"}, method = RequestMethod.GET)
	public void form() {
	}

	@RequestMapping(value =  {"/signon", "/resellerSignon"}, method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String,Object> formJSON() {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("resellerTypes", siteConfiguration.getResellerTypeList());
		return returnMap;
	}



	@RequestMapping(value =  {"/signon", "/resellerSignon"}, method = RequestMethod.POST)
	public String registerAccount(@Valid @ModelAttribute("registrationDTO") RegistrationDTO registrationDTO,
			BindingResult result, @ModelAttribute("profile") Profile profile, Model m, HttpServletRequest request) {
		if (!result.hasErrors()) {
			handleRegistration(registrationDTO, profile);
			//m.addAttribute("message", getPostSuccessCode());
			return "redirect:/";
		}
		return ServletUtil.getRequestURIWithoutContext(request).contains("resellerSignon")? "/resellerSignon" : "/signon";
	}

	


	@RequestMapping(value =  {"/signon", "/resellerSignon"}, method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> registerAccountJSON(@Valid @ModelAttribute("registrationDTO") RegistrationDTO registrationDTO, 
			BindingResult result, @ModelAttribute("profile") Profile profile, Model m, HttpServletRequest request) {
		if (!result.hasErrors()) {
			handleRegistration(registrationDTO, profile);
		}
		Map<String, Object> processPostJSON = processPostJSON(result);
		if(!result.hasErrors()){
			MobileToken mobileToken = new MobileToken();
			mobileToken.setAccountId(profile.getAccountId());
			mobileToken.setClientIdentifier(ServletUtil.getMobileDeviceId(request));
			mobileToken.setUserAgent(ServletUtil.getUserAgent(request));
			mobileToken.setAccessToken(EncryptionUtil.genRandomAccessToken());
			mobileToken.setValidDate(DateUtils.addDays(new Date(), siteConfiguration.getMobileTokenValidDays()));
			accountService.updateOrInsertMobileToken(mobileToken);
			processPostJSON.put(SERVER_RESPONSE_ACCESS_TOKEN_PARAM_KEY, mobileToken.getAccessToken());
			processPostJSON.put(SERVER_RESPONSE_USER_ID_KEY, profile.getAccountId());
		}
		return processPostJSON;
	}



	private void handleRegistration(RegistrationDTO registrationDTO, Profile profile) {
		if(StringUtils.isBlank(registrationDTO.getEmail())){
			registrationDTO.setEmail(null);
		}
		registrationDTO.setPassword(registrationDTO.getPassword().trim());
		if (accountService.registrerAccount(registrationDTO)) {
			profile.setAccountDatasource(accountService.getAccountByPhoneOrEmail(registrationDTO.getPhone()));
			profile.setStatus(Profile.EXPLICIT_LOGIN);
		}
	}

}
