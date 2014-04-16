package com.amateur.service.impl;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.amateur.service.MailSenderService;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service("mailSenderService")
public class MailSenderServiceImpl implements MailSenderService {

	private static final Logger logger = Logger
			.getLogger(MailSenderService.class);
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
			MimeMessage mailMessage = initMessage(dataMap, templatePath);
			Session session = ((JavaMailSenderImpl) mailSender).getSession();
			((JavaMailSenderImpl) mailSender)
					.setUsername(getSessionMailProperty(session, "user"));
			((JavaMailSenderImpl) mailSender)
					.setPassword(getSessionMailProperty(session, "password"));
			((JavaMailSenderImpl) mailSender).setHost(getSessionMailProperty(
					session, "host"));
			mailSender.send(mailMessage);
		} catch (Exception e) {
			logger.error("[Error message]: Error happened when sending email.",
					e);
		}
	}

	private String getSessionMailProperty(Session session, String property) {
		String protocol = session.getProperty("mail.transport.protocol");
		if (!StringUtils.hasText(protocol)) {
			protocol = "smtp";
		}
		StringBuilder builder = new StringBuilder().append("mail.")
				.append(protocol).append(".").append(property);
		String value = session.getProperty(builder.toString());
		return value;
	}

	private MimeMessage initMessage(Map<String, Object> dataMap,
			String templatePath) {
		MimeMessage mailMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(
					mailMessage, true, "utf-8");
			if (!StringUtils.hasText((String) dataMap.get(FROM))) {
				dataMap.put(FROM, "noreply@2soce.com");
			}
			if (!StringUtils.hasText((String) dataMap.get(SUBJECT))) {
				dataMap.put(SUBJECT, "来自2soce的邮件");
			}
			messageHelper.setFrom((String) dataMap.get(FROM));
			messageHelper.setSubject((String) dataMap.get(SUBJECT));
			if ((dataMap.get(EMAIL_TYPE_TO) == null || ((String[]) dataMap
					.get(EMAIL_TYPE_TO)).length == 0)
					&& (dataMap.get(EMAIL_TYPE_CC) == null || ((String[]) dataMap
							.get(EMAIL_TYPE_CC)).length == 0)
					&& (dataMap.get(EMAIL_TYPE_BCC) == null || ((String[]) dataMap
							.get(EMAIL_TYPE_BCC)).length == 0)) {
				logger.error("[Error message]: There are no recipients.");
				return null;
			}
			if (dataMap.get(EMAIL_TYPE_TO) != null
					&& ((String[]) dataMap.get(EMAIL_TYPE_TO)).length > 0) {
				messageHelper.setTo((String[]) dataMap.get(EMAIL_TYPE_TO));
			}
			if (dataMap.get(EMAIL_TYPE_CC) != null
					&& ((String[]) dataMap.get(EMAIL_TYPE_CC)).length > 0) {
				messageHelper.setCc((String[]) dataMap.get(EMAIL_TYPE_CC));
			}
			if (dataMap.get(EMAIL_TYPE_BCC) != null
					&& ((String[]) dataMap.get(EMAIL_TYPE_BCC)).length > 0) {
				messageHelper.setBcc((String[]) dataMap.get(EMAIL_TYPE_BCC));
			}
			Context env = (Context) new InitialContext()
					.lookup("java:comp/env");
			String emailImgUrl = (String) env.lookup(EMAIL_IMG_URL);
			if (StringUtils.hasText(emailImgUrl)) {
				dataMap.put(EMAIL_IMG_URL, emailImgUrl);
			}
			String msgText = getMailText(dataMap, templatePath);
			if (!StringUtils.hasText(msgText)) {
				logger.error("[Error message]: The content of email is empty.");
				return null;
			}
			messageHelper.setText(msgText, true);
		} catch (MessagingException e) {
			logger.error(
					"[Error message]: Error happened when initializing email message.",
					e);
			return null;
		} catch (NamingException e) {
			logger.error(
					"[Error message]: Error happened when search server URL with JNDI.",
					e);
		}
		return mailMessage;
	}

	private String getMailText(Map<String, Object> dataMap, String templatePath) {
		try {
			Template template = freeMarkerConfigurer.getConfiguration()
					.getTemplate(templatePath);
			return FreeMarkerTemplateUtils.processTemplateIntoString(template,
					dataMap);
		} catch (IOException e) {
			logger.error(
					"[Error message]: Error happened when searching email template file.",
					e);
		} catch (TemplateException e) {
			logger.error(
					"[Error message]: Error happened when initializing email template file.",
					e);
		}
		return null;
	}
}