package com.amateur.account.controller;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amateur.account.dto.ForgetPasswordDTO;
import com.amateur.account.service.AccountService;
import com.amateur.account.validator.ForgetPasswordValidator;
import com.amateur.controller.BaseController;
import com.amateur.domain.Account;
import com.amateur.domain.VerificationCode;
import com.amateur.service.impl.MailSenderServiceImpl;


@Controller
public class ForgetPasswordController extends BaseController {
	
	@Autowired
	private ForgetPasswordValidator forgetPasswordValidator;

	@Autowired
	private MailSenderServiceImpl mailSender;
	
	@Autowired
	private AccountService accountService;
	
	@Override
	protected String getSuccessCode() {
		return "apply.reset.password.success";
	}
	
	@Override
	protected String getPostSuccessCode() {
		return "apply.reset.password.success";
	}
	
	@InitBinder("forgetPasswordDTO")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(forgetPasswordValidator);
	}

	@ModelAttribute("forgetPasswordDTO")
	public ForgetPasswordDTO createFormBean() {
		ForgetPasswordDTO forgetPasswordDTO = new ForgetPasswordDTO();
		return forgetPasswordDTO;
	}
	
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
	public void forgetPassword() {
		
	}
	
	
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public String forgetPassword(@Valid @ModelAttribute("forgetPasswordDTO") ForgetPasswordDTO forgetPasswordDTO,BindingResult result,Model m) {
		if(!result.hasErrors()){
			Account account=accountService.getAccountByPhoneOrEmail(forgetPasswordDTO.getLoginName());
			m.addAttribute("bindPhone", account.getBindPhone());
			m.addAttribute("bindMail", account.getBindEmail());
			m.addAttribute("phone", account.getPhone());
			m.addAttribute("mail", account.getEmail());
			m.addAttribute("nickName", account.getNickname());
			return "findPassword";
		}
		return "forgetPassword";
	}
	
	
	@RequestMapping(value = "/findPassword", method = RequestMethod.GET)
	public String findPassword() {
		return "redirect:forgetPassword";
	}
	
	@RequestMapping(value = "/findPassword", method = RequestMethod.POST)
	public String findPassword(HttpServletRequest req,Model m,RedirectAttributes redirectAttributes) {
		String findPasswordMethod=req.getParameter("method");
		if(StringUtils.isBlank(findPasswordMethod)){
			return "redirect:forgetPassword";
		}
		if("email".equals(findPasswordMethod)){
			String mail=req.getParameter("mail");
			return sendFindPasswordMail(mail,m);
		}
		if("mobile".equals(findPasswordMethod)){
			redirectAttributes.addFlashAttribute("phone", req.getParameter("phone"));
			return "redirect:resetPasswordByPhone";
		}
		return "redirect:forgetPassword";
	}
	
	
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String,Object> forgetPassword(@Valid @ModelAttribute("forgetPasswordDTO") ForgetPasswordDTO forgetPasswordDTO,BindingResult result) {
		Map<String,Object> resultMap=processPostJSON(result);
		if(!result.hasErrors()){
			Account account=accountService.getAccountByPhoneOrEmail(forgetPasswordDTO.getLoginName());
			resultMap.put("phone", account.getPhone());
		}
		return resultMap;
	}
	
	private String sendFindPasswordMail(String mail,Model m) {
			Account account=accountService.getAccountByEmail(mail);
			if(account==null){
				return "forgetPassword";
			}
			Map<String,Object> dataMap=new HashMap<String,Object>();
			String username=account.getRealName()==null?mail:account.getRealName();
			dataMap.put("username",mail);
			dataMap.put("realname",username);
			String code=UUID.randomUUID().toString().replace("-", "");
			dataMap.put("activeCode",code );
			Calendar calendar=Calendar.getInstance();
			calendar.add(Calendar.HOUR, 1);
			Date date=calendar.getTime();
			dataMap.put("expireDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			dataMap.put("from", "testForgetpwd@163.com");
			dataMap.put("subject", "[2soce的邮件]找回密码");
			dataMap.put("email_type_to", new String []{mail});
			VerificationCode record =new VerificationCode();
			record.setPrinciple("'"+mail+"'");
			record.setType(3);
			record.setUsed(false);
			record.setUsedDate(null);
			record.setValidDate(date);
			record.setVerificationCode(code);
			accountService.addVerificationCode(record);
			mailSender.sendMail(dataMap, "emailForPassword.ftl");
			m.addAttribute("sendMail", messageSource.getMessage("forget.password.send.mail.success", new String[]{mail}, Locale.getDefault()));
			return "applyFindPasswordSuccess";
	}
}
