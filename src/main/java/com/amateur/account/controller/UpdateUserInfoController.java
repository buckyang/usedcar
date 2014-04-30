package com.amateur.account.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
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

import com.amateur.account.dto.UserInfoDTO;
import com.amateur.account.service.AccountService;
import com.amateur.account.validator.UpdateUserInfoValidator;
import com.amateur.controller.BaseController;
import com.amateur.domain.Account;
import com.amateur.domain.Address;
import com.amateur.session.Profile;

@Controller
@RequestMapping("account")
@SessionAttributes("profile")
public class UpdateUserInfoController extends BaseController {

	private static final Logger logger = Logger.getLogger(UpdateUserInfoController.class);
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private UpdateUserInfoValidator userInfoValidator;

	@Override
	protected String getPostSuccessCode() {
		return "update.user.info.success";
	}

	@InitBinder("userInfoDTO")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userInfoValidator);
	}

	@ModelAttribute("userInfoDTO")
	public UserInfoDTO createFormBean(@ModelAttribute("profile") Profile profile) {
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO.setAccountId(profile.getAccountId());
		return userInfoDTO;
	}


	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	public void updateUserInfo(@Valid @ModelAttribute("userInfoDTO") UserInfoDTO userInfoDTO,BindingResult result, Model mode) {
		if (!result.hasErrors()) {
			Account account = new Account();
			account.setAccountId(userInfoDTO.getAccountId());
			BeanUtils.copyProperties(userInfoDTO, account, new String[] {"password", "birthdate", "updateTime", "registrationDate","accountType", "profileHash" });
			String source = new StringBuilder(userInfoDTO.getBirthyear())
					.append("-").append(userInfoDTO.getBirthmonth())
					.append("-").append(userInfoDTO.getBirthday()).toString();
			try {
				account.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse(source));
			} catch (ParseException e) {
				logger.error("The birthdate's pattern is error."+source);
			}
			Address newHomeAddress = new Address();
			BeanUtils.copyProperties(userInfoDTO, newHomeAddress,new String []{"addressId"});
			newHomeAddress.setAccountId(userInfoDTO.getAccountId());
			
			accountService.updateUserInfo(account);
			accountService.updateHomeAddress(newHomeAddress);
			mode.addAttribute("message", getPostSuccessMesage());
		}
		loadUserInfo(userInfoDTO);
		mode.addAttribute(userInfoDTO);
	}

	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> updateUserInfoJSON(@Valid @ModelAttribute("userInfoDTO") UserInfoDTO userInfoDTO,BindingResult result) {
		if (!result.hasErrors()) {
			Account account = new Account();
			BeanUtils.copyProperties(userInfoDTO, account, new String[] {
					"password", "birthdate", "updateTime", "registrationDate",
					"accountType", "profileHash" });
			String source = new StringBuilder(userInfoDTO.getBirthyear())
					.append("-").append(userInfoDTO.getBirthmonth())
					.append("-").append(userInfoDTO.getBirthday()).toString();
			try {
				account.setBirthdate(new SimpleDateFormat("yyyy-MM-dd")
						.parse(source));
			} catch (ParseException e) {
				logger.error("The birthdate's pattern is error."+source);
			}
			account.setAccountId(userInfoDTO.getAccountId());
			
			Address newHomeAddress = new Address();
			newHomeAddress.setAccountId(userInfoDTO.getAccountId());
			BeanUtils.copyProperties(userInfoDTO, newHomeAddress,new String []{"addressId"});
			
			accountService.updateUserInfo(account);
			accountService.updateHomeAddress(newHomeAddress);
		}
		Map<String , Object> resultMap=processPostJSON(result);
		return resultMap;
	}

	private void loadUserInfo(UserInfoDTO userInfoDTO) {
		Integer accountId = userInfoDTO.getAccountId();
		Account account = accountService.getAccountById(accountId);
		BeanUtils.copyProperties(account, userInfoDTO, new String[] {
				"birthyear", "birthmonth", "birthday", "province", "city",
				"county", "street" });
		Date birthdate = account.getBirthdate();
		if (birthdate != null) {
			String[] year_month_day = new SimpleDateFormat("yyyy-MM-dd")
					.format(birthdate).split("-");
			userInfoDTO.setBirthyear(year_month_day[0]);
			userInfoDTO.setBirthmonth(year_month_day[1]);
			userInfoDTO.setBirthday(year_month_day[2]);
		}
		Address address = accountService.getHomeAddressByAccountId(accountId);
		BeanUtils.copyProperties(address, userInfoDTO, new String[] {
				"accountId", "addressId", "birthday" });
	}

}
