package com.amateur.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.amateur.configuration.SiteConfiguration;

public class AdminBaseController {
	public static final String	POST_SUCESS_KEY				= "post.sucess";
	public static final String	EXECUTION_RESULT_PARAM_KEY	= "executionResult";
	public static final String	MESSAGE_PARAM_KEY			= "message";
	public static final String	ACCESS_TOKEN_PARAM_KEY		= "accessToken";
	private static final int	MAX_ERROR_MSGS				= 3;
	private static final String	ERROR_MSG_DELIMITER			= " ";
	@Autowired
	protected MessageSource		messageSource;
	@Autowired
	protected SiteConfiguration siteConfiguration;
	
	private static final Logger	logger						= Logger.getLogger(AdminBaseController.class);



	protected Map<String, Object> processPostJSON(BindingResult result) {
		Map<String, Object> postResultJSON = new LinkedHashMap<String, Object>();
		if (result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			StringBuilder errorMsg = new StringBuilder();
			for(int i = 0; i < allErrors.size() &&  i< MAX_ERROR_MSGS; i++){
				ObjectError objectError = allErrors.get(i);
				String errorFieldOrCommand = null;
				if (objectError instanceof FieldError) {
					errorFieldOrCommand = ((FieldError)objectError).getField();
				} else {
					errorFieldOrCommand = objectError.getObjectName();

				}
				logger.debug("Error field: " + errorFieldOrCommand);
				String[] codes = objectError.getCodes();
				for (String code : codes) {
					String message = null;
					try {
						message = messageSource.getMessage(code, null, null);
					} catch (NoSuchMessageException e) {
					}
					if (message != null) {
						if(i != 0){
							errorMsg.append(ERROR_MSG_DELIMITER);
						}
						errorMsg.append(message);
					}
				}
			}
			postResultJSON.put(EXECUTION_RESULT_PARAM_KEY, false);
			postResultJSON.put(MESSAGE_PARAM_KEY, errorMsg.toString());
			
		}else{
			postResultJSON.put(EXECUTION_RESULT_PARAM_KEY, true);
			postResultJSON.put(MESSAGE_PARAM_KEY, messageSource.getMessage(getPostSuccessCode() == null ? POST_SUCESS_KEY
					: getPostSuccessCode(), null, null));
		}
		return postResultJSON;
	}



	protected String getPostSuccessCode() {
		return null;
	}



	protected String getPostSuccessMesage() {
		return getPostSuccessCode() == null ? null : messageSource.getMessage(getPostSuccessCode(), null, null);
	}
}
