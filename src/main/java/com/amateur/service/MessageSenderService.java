package com.amateur.service;

public interface MessageSenderService {

	String send(String url,String mobile, String content,String sendTime);
}