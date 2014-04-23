package com.amateur.account.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.amateur.account.service.AccountService;
import com.amateur.controller.BaseController;
import com.amateur.session.Profile;

@Controller
@RequestMapping("account")
@SessionAttributes("profile")
public class VerificationCodeController extends BaseController {
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/obtainCode", method = RequestMethod.GET)
	public void obtainCode(@ModelAttribute("profile") Profile profile,Model mode) {
		String code=accountService.obtainVerificationCode();
		mode.addAttribute("code",code);
	}
	
	@RequestMapping(value = "/obtainCode", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String,Object> obtainCodeJSON(@ModelAttribute("profile") Profile profile,Model mode,BindingResult result) {
		String code=accountService.obtainVerificationCode();
		Map<String,Object> resultMap=processGETJSON(true);
		resultMap.put("code", code);
		return resultMap;
	}
}
