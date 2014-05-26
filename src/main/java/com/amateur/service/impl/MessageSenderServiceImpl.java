package com.amateur.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.amateur.service.MessageSenderService;

public class MessageSenderServiceImpl implements MessageSenderService {
	
	private static final Logger logger = Logger.getLogger(MessageSenderService.class);

	private String userid;
	private String account;
	private String password;

	@Override
	public String send(String reqURL,String mobile, String content,String sendTime) {
		StringBuffer receive = new StringBuffer();
		BufferedReader br = null;
		DataOutputStream dos=null;
		try {
			StringBuffer sendParams=new StringBuffer("action=send").append("&userid=").append(getUserid()).append("&account=").append(getAccount())
					.append("&password=").append(getPassword()).append("&mobile=").append(mobile).append("&content=").append(content);
			
			if(!StringUtils.isBlank(sendTime)){
				sendParams.append("&sendTime=").append(sendTime);
			}
			
			URL url = new URL(reqURL);
			HttpURLConnection comnection = (HttpURLConnection) url.openConnection();

			comnection.setDoOutput(true);
			comnection.setDoInput(true);
			((HttpURLConnection) comnection).setRequestMethod("POST");
			comnection.setUseCaches(false);
			comnection.setAllowUserInteraction(true);
			HttpURLConnection.setFollowRedirects(true);
			comnection.setInstanceFollowRedirects(true);

			comnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			comnection.setRequestProperty("Content-Length", String.valueOf(sendParams.toString().getBytes().length));

			dos = new DataOutputStream(comnection.getOutputStream());
			dos.write(sendParams.toString().getBytes("UTF-8"));

			br = new BufferedReader(new InputStreamReader(comnection.getInputStream(), "UTF-8"));
			String line;
			while ((line = br.readLine()) != null) {
				receive.append(line).append("\r\n");
			}
		} catch (IOException e) {
			logger.error("send message failed.content is ::"+content,e);
		} finally {
			try {
				if (dos != null) {
					dos.close();
				}
			} catch (IOException e) {
				logger.error("close the out put stream error",e);
			}
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				logger.error("close the reader stream error",e);
			}
		}
		return receive.toString();
	}


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
