package com.amateur.persistence;

import com.amateur.domain.ResetPasswordRecord;

public interface ResetPasswordRecordMapper {

	ResetPasswordRecord findRecordByAccountId(Integer accountId);
	
	void insertResetPasswordRecord(ResetPasswordRecord record);
	
	void deleteResetPasswordRecord(ResetPasswordRecord record);
	
	void updateResetPasswordRecod(ResetPasswordRecord record);
}
