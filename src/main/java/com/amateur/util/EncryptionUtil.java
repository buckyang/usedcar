package com.amateur.util;

import java.io.File;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil implements InitializingBean {
	
	private static final Logger logger = Logger.getLogger(EncryptionUtil.class);
	
	private Resource passwordPublicKey;
	
	private static byte[] passwordPublicKeyBytes;
	
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
