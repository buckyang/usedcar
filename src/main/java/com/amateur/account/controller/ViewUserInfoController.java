package com.amateur.account.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.amateur.account.dto.UserInfoDTO;
import com.amateur.account.service.AccountService;
import com.amateur.controller.BaseController;
import com.amateur.domain.Account;
import com.amateur.domain.Address;
import com.amateur.session.Profile;



@Controller
@RequestMapping("account")
@SessionAttributes("profile")
public class ViewUserInfoController extends BaseController {

private static final Logger logger = Logger.getLogger(UpdateUserInfoController.class);
	
	@Autowired
	private AccountService accountService;
	
	@Override
	protected String getGetErrorCode() {
		return "view.user.info.failed";
	}

	@Override
	protected String getGetSuccessCode() {
		return "view.user.info.success";
	}
	
	@ModelAttribute("userInfoDTO")
	public UserInfoDTO createFormBean(@ModelAttribute("profile") Profile profile) {
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO.setAccountId(profile.getAccountId());
		return userInfoDTO;
	}

	@RequestMapping(value = "/viewUserInfo", method = RequestMethod.GET)
	public void viewUserInfo(@Valid @ModelAttribute("userInfoDTO") UserInfoDTO userInfoDTO,Model mode) {
			UserInfoDTO result=loadUserInfo(userInfoDTO);
			if(result==null){
				logger.warn("view user info failed,user id is::"+userInfoDTO.getAccountId());
				String errorMessage=messageSource.getMessage("view.user.info.failed", null, Locale.getDefault());
				mode.addAttribute("errorMessage", errorMessage);
			}else{
				mode.addAttribute(result);
			}
	}

	@RequestMapping(value = "/viewUserInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String,Object> viewUserInfoJSON(@ModelAttribute("userInfoDTO") UserInfoDTO userInfoDTO) {
		Map<String , Object> resultMap=null;
		UserInfoDTO result=loadUserInfo(userInfoDTO);
		if(result==null){
			logger.warn("view user info failed,user id is::"+userInfoDTO.getAccountId());
			resultMap=processGenericJSON(false);
		}else{
			resultMap=processGenericJSON(true);
			resultMap.put("nickname", result.getNickname());
			resultMap.put("realName", result.getRealName());
			resultMap.put("email", result.getEmail());
			resultMap.put("bindEmail", result.getBindEmail());
			resultMap.put("sex", result.getSex());
			resultMap.put("phone", result.getPhone());
			resultMap.put("bindPhone", result.getBindPhone());
			resultMap.put("birthyear", result.getBirthyear());
			resultMap.put("birthmonth", result.getBirthmonth());
			resultMap.put("birthday", result.getBirthday());
			resultMap.put("certificateNumber", result.getCertificateNumber());
			resultMap.put("province", result.getProvince());
			resultMap.put("city", result.getCity());
			resultMap.put("county", result.getCounty());
			resultMap.put("street", result.getStreet());
		}
		return resultMap;
	}
	
	private UserInfoDTO loadUserInfo(UserInfoDTO userInfoDTO) {
		Integer accountId = userInfoDTO.getAccountId();
		Account account = accountService.getAccountById(accountId);
		if(account==null){
			return null;
		}
		BeanUtils.copyProperties(account, userInfoDTO, new String[] {"birthyear", "birthmonth", "birthday", "province", "city","county", "street" });
		Date birthdate = account.getBirthdate();
		if (birthdate != null) {
			String[] year_month_day = new SimpleDateFormat("yyyy-MM-dd").format(birthdate).split("-");
			userInfoDTO.setBirthyear(year_month_day[0]);
			userInfoDTO.setBirthmonth(year_month_day[1]);
			userInfoDTO.setBirthday(year_month_day[2]);
		}
		Address address = accountService.getHomeAddressByAccountId(accountId);
		BeanUtils.copyProperties(address, userInfoDTO, new String[] {"accountId", "addressId", "birthday" });
		return userInfoDTO;
	}
	
}
