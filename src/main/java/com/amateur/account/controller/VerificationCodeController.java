package com.amateur.account.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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

import com.amateur.account.dto.ObtainCodeDTO;
import com.amateur.account.service.AccountService;
import com.amateur.account.validator.ObtainVerificationCodeValidator;
import com.amateur.controller.BaseController;
import com.amateur.domain.VerificationCode;
import com.amateur.service.impl.MessageSenderServiceImpl;
import com.amateur.session.Profile;

@Controller
@RequestMapping("account")
@SessionAttributes("profile")
public class VerificationCodeController extends BaseController {

	@Autowired
	private ObtainVerificationCodeValidator validator;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private MessageSenderServiceImpl messageSender;
	
	@Override
	protected String getPostSuccessCode() {
		return "obtain.code.success";
	}

	@InitBinder("obtainCodeDTO")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@ModelAttribute("obtainCodeDTO")
	public ObtainCodeDTO createFormBean() {
		ObtainCodeDTO obtainCodeDTO = new ObtainCodeDTO();
		return obtainCodeDTO;
	}

	@RequestMapping(value = "/obtainCode", method = RequestMethod.POST)
	public void obtainCode(
			@Valid @ModelAttribute("obtainCodeDTO") ObtainCodeDTO obtainCodeDTO,BindingResult result,
			@ModelAttribute("profile") Profile profile, Model mode) {
		if(!result.hasErrors()){
			mode.addAttribute(MESSAGE_PARAM_KEY, sendMessage(obtainCodeDTO));
		}else{
			mode.addAttribute(MESSAGE_PARAM_KEY, messageSource.getMessage("invalid.phone.number", null, Locale.getDefault()));
		}
	}


	@RequestMapping(value = "/obtainCode", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> obtainCodeJSON(@Valid @ModelAttribute("obtainCodeDTO") ObtainCodeDTO obtainCodeDTO,BindingResult result,
			@ModelAttribute("profile") Profile profile) {
		Map<String, Object> resultMap = processPostJSON(result);
		boolean hasSended=false;
		if(!result.hasErrors()){
			hasSended=sendMessage(obtainCodeDTO);
		}
		resultMap.put(EXECUTION_RESULT_PARAM_KEY, hasSended);
		if(!hasSended){
			resultMap.put(MESSAGE_PARAM_KEY, messageSource.getMessage("obtain.verification.code.failed", null, Locale.getDefault()));
		}
		return resultMap;
	}
	
	private boolean sendMessage(ObtainCodeDTO obtainCodeDTO) {
		String code = accountService.obtainVerificationCode();
		String message=messageSource.getMessage("obtain.code.message", new String[]{code}, Locale.getDefault());
		String returnValue=messageSender.send("http://dc.28inter.com/sms.aspx", obtainCodeDTO.getPhoneNumber(), message, null);
		if(returnValue.indexOf("Success")>-1){
			Calendar calendar=Calendar.getInstance();
			calendar.add(Calendar.HOUR, 1);
			Date date=calendar.getTime();
			VerificationCode record =new VerificationCode();
			record.setPrinciple( obtainCodeDTO.getPhoneNumber());
			record.setType(obtainCodeDTO.getType());
			record.setUsed(false);
			record.setUsedDate(null);
			record.setValidDate(date);
			record.setVerificationCode(code);
			accountService.addVerificationCode(record);
			return true;
		}
		return false;
	}
}
