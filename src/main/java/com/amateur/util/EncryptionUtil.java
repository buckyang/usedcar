package com.amateur.util;

import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil implements InitializingBean {

	private static final Logger	logger								= Logger.getLogger(EncryptionUtil.class);

	private static final String	MOBILE_TOKEN_PRIVATE_KEY			= "uja6snx21b";

	private static final String	MOBILE_TOKEN_USER_TIME_DELIMITER	= "&";

	private static final int	MOBILE_TOKEN_MD5_LENGTH				= 32;

	private Resource			passwordPublicKey;

	private static byte[]		passwordPublicKeyBytes;



	public static String encryptPassword(String plaintText) {
		String encodedString = null;
		try {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(passwordPublicKeyBytes);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			PublicKey pubKey = kf.generatePublic(keySpec);

			Cipher encryptCipher = Cipher.getInstance("RSA/ECB/NoPadding");
			encryptCipher.init(Cipher.ENCRYPT_MODE, pubKey);
			byte[] encryptedBytes = encryptCipher.doFinal(StringUtils.getBytesUtf8(plaintText));
			encodedString = Base64.encodeBase64String(encryptedBytes);
		} catch (Exception e) {
			logger.debug("Encypting password failed", e);
			return plaintText;
		}
		return encodedString;
	}



	public static String getMD5HashValue(String message) {
		String hashValue = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(StringUtils.getBytesUtf8(message));
			hashValue = Hex.encodeHexString(md.digest());
		} catch (NoSuchAlgorithmException e) {

		}
		return hashValue;
	}



	public static String getBase64MD5HashValue(String message) {
		String hashValue = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(StringUtils.getBytesUtf8(message));
			hashValue = Base64.encodeBase64String(md.digest());
		} catch (NoSuchAlgorithmException e) {

		}
		return hashValue;
	}



	public static String generateMobileRequestAccessToken(String userId, String token) {
		String currentTime = Long.toString(System.currentTimeMillis());
		String plainText = token + MOBILE_TOKEN_PRIVATE_KEY + currentTime;
		String plainTextHashValue = getMD5HashValue(plainText);
		String requestAccessToken = plainTextHashValue + userId + MOBILE_TOKEN_USER_TIME_DELIMITER + currentTime;
		return Base64.encodeBase64String(requestAccessToken.getBytes());
	}



	public static String decodeUserIdFromReqeustAccessToken(String base64AccessToken) {
		String requestAccessToken = new String(Base64.decodeBase64(base64AccessToken));
		int userTimeDelimeter = requestAccessToken.indexOf(MOBILE_TOKEN_USER_TIME_DELIMITER);
		if (userTimeDelimeter > MOBILE_TOKEN_MD5_LENGTH & requestAccessToken.length() > MOBILE_TOKEN_MD5_LENGTH + 2) {
			return requestAccessToken.substring(MOBILE_TOKEN_MD5_LENGTH, userTimeDelimeter);
		}
		return null;
	}



	public static boolean validMobileRequestToken(String base64AccessToken, String token) {
		String requestAccessToken = new String(Base64.decodeBase64(base64AccessToken));

		if (requestAccessToken.length() > MOBILE_TOKEN_MD5_LENGTH + 3) {
			String tokenMD5 = requestAccessToken.substring(0, MOBILE_TOKEN_MD5_LENGTH);
			String time = org.apache.commons.lang3.StringUtils.substringAfter(requestAccessToken,
					MOBILE_TOKEN_USER_TIME_DELIMITER);
			if (tokenMD5.equals(getMD5HashValue(token + MOBILE_TOKEN_PRIVATE_KEY + time))) {
				return true;
			}
		}
		return false;

	}



	private static String genRandomAlphaAndNum(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = length; i > 0; i -= 12) {
			int n = Math.min(12, Math.abs(i));
			sb.append(org.apache.commons.lang3.StringUtils.leftPad(
					Long.toString(Math.round(Math.random() * Math.pow(36, n)), 36), n, '0'));
		}
		return sb.toString();
	}

	public static String genRandomAccessToken(){
		return genRandomAlphaAndNum(8);
	}



	@Override
	public void afterPropertiesSet() throws Exception {
		passwordPublicKeyBytes = FileUtils.readFileToByteArray(getPasswordPublicKey().getFile());
	}



	public Resource getPasswordPublicKey() {
		return passwordPublicKey;
	}



	public void setPasswordPublicKey(Resource passwordPublicKey) {
		this.passwordPublicKey = passwordPublicKey;
	}

}
