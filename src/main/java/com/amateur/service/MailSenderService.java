package com.amateur.service;

import java.util.Map;

public interface MailSenderService {

	public void sendMail(Map<String, Object> dataMap, String templatePath);
	
}
