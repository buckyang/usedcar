package com.amateur.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class BaseController {
	public static final String	POST_SUCESS_KEY	= "post.sucess";
	@Autowired
	protected MessageSource	messageSource;



	protected PostResultJSON processPostJSON(BindingResult result) {
		PostResultJSON postResultJSON = null;
		if (result.hasErrors()) {
			List<List<String>> errorList = new ArrayList<List<String>>();
			List<ObjectError> allErrors = result.getAllErrors();
			for (ObjectError objectError : allErrors) {
				List<String> singleError = new ArrayList<String>();
				if (objectError instanceof FieldError) {
					singleError.add(((FieldError) objectError).getField());
				} else {
					singleError.add(objectError.getObjectName());
				}
				String[] codes = objectError.getCodes();
				for (String code : codes) {
					String message = null;
					try {
						message = messageSource.getMessage(code, null, null);
					} catch (NoSuchMessageException e) {
					}
					if (message != null) {
						singleError.add(message);
					}
				}
				errorList.add(singleError);
			}
			postResultJSON = new PostResultJSON(errorList);
		} else {
			postResultJSON = new PostResultJSON(messageSource.getMessage(getPostSuccessCode() == null ? POST_SUCESS_KEY
					: getPostSuccessCode(), null, null));
		}
		return postResultJSON;
	}



	protected String getPostSuccessCode() {
		return null;
	}

	protected String getPostSuccessMesage(){
		return getPostSuccessCode()==null?null:messageSource.getMessage(getPostSuccessCode(), null,null);
	}
	
	protected class PostResultJSON {

		public PostResultJSON(String succesMsg) {
			super();
			this.hasErrors = false;
			this.succesMsg = succesMsg;
		}



		public PostResultJSON(List<List<String>> errorMsgs) {
			super();
			this.hasErrors = true;
			this.errorMsgs = errorMsgs;
		}

		private boolean				hasErrors;
		private List<List<String>>	errorMsgs;
		private String				succesMsg;
		private Map<String, Object>	successExtraParams;



		public boolean isHasErrors() {
			return hasErrors;
		}



		public void setHasErrors(boolean hasErrors) {
			this.hasErrors = hasErrors;
		}



		public String getSuccesMsg() {
			return succesMsg;
		}



		public void setSuccesMsg(String succesMsg) {
			this.succesMsg = succesMsg;
		}



		public List<List<String>> getErrorMsgs() {
			return errorMsgs;
		}



		public void setErrorMsgs(List<List<String>> errorMsgs) {
			this.errorMsgs = errorMsgs;
		}



		public Map<String, Object> getSuccessExtraParams() {
			return successExtraParams;
		}



		public void setSuccessExtraParams(Map<String, Object> successExtraParams) {
			this.successExtraParams = successExtraParams;
		}

	}
}
