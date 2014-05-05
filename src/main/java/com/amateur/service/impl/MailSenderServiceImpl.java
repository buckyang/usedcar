package com.amateur.service.impl;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.amateur.service.MailSenderService;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service("mailSenderService")
public class MailSenderServiceImpl implements MailSenderService {

	private static final Logger logger = Logger.getLogger(MailSenderService.class);
	public static final String FROM = "from";
	public static final String SUBJECT = "subject";
	public static final String EMAIL_TYPE_TO = "email_type_to";
	public static final String EMAIL_TYPE_CC = "email_type_cc";
	public static final String EMAIL_TYPE_BCC = "email_type_bcc";
	public static final String EMAIL_IMG_URL = "emailImgUrl";

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@Override
	public void sendMail(Map<String, Object> dataMap, String templatePath) {
		try {
			MimeMessage mailMessage = createMailMessage(dataMap, templatePath);
			mailSender.send(mailMessage);
		} catch (Exception e) {
			logger.error("[Error message]: Error happened when sending email.",e);
		}
	}

	private MimeMessage createMailMessage(Map<String, Object> dataMap,String templatePath) {
		String [] mailTo=(String[]) dataMap.get(EMAIL_TYPE_TO);
		
		if(mailTo==null||mailTo.length<1){
			return null;
		}
		
		String msgText = getMailText(dataMap, templatePath);
		if (StringUtils.isBlank(msgText)) {
			logger.warn("[Error message]: The content of email is empty.");
			return null;
		}
		
		if(dataMap.get(FROM)==null){
			logger.info("There is no mail sender ,so used default sender.");
			dataMap.put(FROM, "noreply@2soce.com");
		}
		
		if(dataMap.get(SUBJECT)==null){
			logger.info("There is no mail subject ,so used default subject.");
			dataMap.put(SUBJECT, "[来自2soce的邮件]");
		}
		
		try {
			MimeMessage mailMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
			messageHelper.setFrom((String) dataMap.get(FROM));
			messageHelper.setSubject((String) dataMap.get(SUBJECT));
			messageHelper.setTo(mailTo);

			String [] mailCC=(String[]) dataMap.get(EMAIL_TYPE_CC) ;
			String [] mailBCC=(String[]) dataMap.get(EMAIL_TYPE_BCC);
			if(mailCC!=null&&mailCC.length>0){
				messageHelper.setCc(mailCC);
			}
			if(mailBCC!=null&&mailBCC.length>0){
				messageHelper.setCc(mailBCC);
			}
			messageHelper.setText(msgText, true);
			return mailMessage;
		} catch (MessagingException e) {
			logger.error("[Error message]: Error happened when create email message.",e);
		}
		return null;
	}

	private String getMailText(Map<String, Object> dataMap, String templatePath) {
		try {
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templatePath);
			return FreeMarkerTemplateUtils.processTemplateIntoString(template,dataMap);
		} catch (IOException e) {
			logger.error("[Error message]: Error happened when searching email template file.",e);
		} catch (TemplateException e) {
			logger.error("[Error message]: Error happened when initializing email template file.",e);
		}
		return null;
	}
}