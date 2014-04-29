package com.amateur.account.controller;

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

import com.amateur.account.dto.PhoneNumberDTO;
import com.amateur.account.service.AccountService;
import com.amateur.account.validator.PhoneNumberValidator;
import com.amateur.controller.BaseController;
import com.amateur.session.Profile;

@Controller
@RequestMapping("account")
@SessionAttributes("profile")
public class VerificationCodeController extends BaseController {

	@Autowired
	private PhoneNumberValidator phoneNumberValidator;

	@Autowired
	private AccountService accountService;
	
	@Override
	protected String getPostSuccessCode() {
		return "obtain.code.success";
	}

	@InitBinder("phoneNumberDTO")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(phoneNumberValidator);
	}

	@ModelAttribute("phoneNumberDTO")
	public PhoneNumberDTO createFormBean() {
		PhoneNumberDTO phoneNumberDTO = new PhoneNumberDTO();
		return phoneNumberDTO;
	}

	@RequestMapping(value = "/obtainCode", method = RequestMethod.POST)
	public void obtainCode(
			@Valid @ModelAttribute("phoneNumberDTO") PhoneNumberDTO phoneNumberDTO,
			@ModelAttribute("profile") Profile profile, Model mode,
			BindingResult result) {
		String code = accountService.obtainVerificationCode();
		mode.addAttribute("code", code);
	}

	@RequestMapping(value = "/obtainCode", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> obtainCodeJSON(@Valid @ModelAttribute("phoneNumberDTO") PhoneNumberDTO phoneNumberDTO,BindingResult result,
			@ModelAttribute("profile") Profile profile) {
		Map<String, Object> resultMap = processPostJSON(result);
		if(!result.hasErrors()){
			String code = accountService.obtainVerificationCode();
			resultMap.put("code", code);
		}
		return resultMap;
	}
}
