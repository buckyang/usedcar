package com.amateur.mobiletoken.controller;

import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.amateur.controller.BaseController;
import com.amateur.mobiletoken.dto.AccessTokenDTO;
import com.amateur.mobiletoken.validator.AccessTokenValidator;
import com.amateur.session.Profile;
import com.amateur.util.EncryptionUtil;

@Controller
public class AccessTokenController extends BaseController{

	@Autowired
	private AccessTokenValidator acessTokenValidator;


	@InitBinder("accessTokenDTO")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(acessTokenValidator);
	}
	
	@ModelAttribute("accessTokenDTO")
	public AccessTokenDTO createFormBean() {
		return new AccessTokenDTO();
	}
	
	
	@RequestMapping(value = "/generateAccessToken", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public  Map<String, Object> loginJSON(@Valid @ModelAttribute("accessTokenDTO") AccessTokenDTO accessTokenDTO,
			BindingResult result, @ModelAttribute("profile")Profile profile, Model m, HttpServletRequest request,  HttpServletResponse response) {
		Map<String, Object> processPostJSON = processPostJSON(result);
		if(!result.hasErrors()){
			String sampleAccessToken = EncryptionUtil.generateMobileRequestAccessToken(accessTokenDTO.getUserId(), accessTokenDTO.getAccessToken());
			processPostJSON.put("sampleAccessToken", sampleAccessToken);
		}
		return processPostJSON;
	}	

}