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

import com.amateur.account.dto.UpdatePasswordDTO;
import com.amateur.account.service.AccountService;
import com.amateur.account.validator.UpdatePasswordValidator;
import com.amateur.controller.BaseController;
import com.amateur.domain.Account;
import com.amateur.session.Profile;
import com.amateur.util.EncryptionUtil;

@Controller
@RequestMapping("account")
@SessionAttributes("profile")
public class UpdatePasswordController extends BaseController {
	
	
	@Autowired
	private UpdatePasswordValidator updatePasswordValidator;
	
	@Autowired
	private AccountService accountService;
	
	@Override
	protected String getPostSuccessCode() {
		return "update.password.success";
	}
	
	@InitBinder("updatePasswordDTO")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(updatePasswordValidator);
	}

	@ModelAttribute("updatePasswordDTO")
	public UpdatePasswordDTO createFormBean(@ModelAttribute("profile") Profile profile) {
		UpdatePasswordDTO updatePasswordDTO = new UpdatePasswordDTO();
		updatePasswordDTO.setAccountId(profile.getAccountId());
		return updatePasswordDTO;
	}
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
	public void form() {
	}
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public void updatePassowrd(@Valid @ModelAttribute("updatePasswordDTO") UpdatePasswordDTO updatePasswordDTO,
			BindingResult result, Model m) {
		if (!result.hasErrors()) {
			handleUpdatePassword(updatePasswordDTO, m);
			m.addAttribute("message", getPostSuccessMesage());
		}
	}
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public  Map<String, Object> updatePassowrdJSON(@Valid @ModelAttribute("updatePasswordDTO") UpdatePasswordDTO updatePasswordDTO,
			BindingResult result,@ModelAttribute("profile")Profile profile, Model m) {
		if (!result.hasErrors()) {
			handleUpdatePassword(updatePasswordDTO, m);
		}
		return 	processPostJSON(result);
	}	
	
	private void handleUpdatePassword(UpdatePasswordDTO updatePasswordDTO,
			Model m) {
		Account account = new Account();
		account.setAccountId(updatePasswordDTO.getAccountId());
		account.setPassword(EncryptionUtil.encryptPassword(updatePasswordDTO.getNewPWD()));
		accountService.updatePassword(account);
	}

}
