package com.amateur.persistence;

import java.util.List;

import com.amateur.domain.VerificationCode;


public interface VerificationCodeMapper {

	List<VerificationCode> getCodeByPrinciple(VerificationCode tempCodeCarrier);
	
	void addVerificationCode(VerificationCode code);
	
	void updateVerificationStatus(VerificationCode code);
}
