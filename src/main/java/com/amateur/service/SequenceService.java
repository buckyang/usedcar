package com.amateur.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amateur.domain.Sequence;
import com.amateur.persistence.SequenceMapper;

@Service
public class SequenceService {
	@Autowired
	private SequenceMapper sequenceMapper;

	private String account;
	
	private Map<String, String> sequenceInitMap;
	
	public int getAccountId(){
		return getNextId(getAccount());
	}

	private int getNextId(String primaryKey) {
		return getNextSequence(primaryKey).getNextId();
	}

	private String getNextStringId(String primaryKey) {
		Sequence sequence = getNextSequence(primaryKey);
		return (sequence.getPrefix() != null ? sequence.getPrefix() : "")
				+ sequence.getNextId()
				+ (sequence.getSuffix() != null ? sequence.getSuffix() : "");
	}

	public Sequence getNextSequence(String name) {
		Sequence sequence = sequenceMapper.getSequence(name);
		if (sequence == null) {
			sequence = new Sequence();
			String sequenceInit = sequenceInitMap.get(name);
			String[] initArray = sequenceInit.split(";");
			if (StringUtils.isNotBlank(initArray[0])) {
				sequence.setPrefix(initArray[0]);
			}
			if (StringUtils.isNotBlank(initArray[2])) {
				sequence.setSuffix(initArray[2]);
			}
			sequence.setName(name);
			sequence.setNextId(NumberUtils.toInt(initArray[1]));
			sequenceMapper.insertSequence(sequence);
		}
		sequence.setNextId(sequence.getNextId() + 1);
		sequenceMapper.updateSequence(sequence);
		return sequence;
	}

	public Map<String, String> getSequenceInitMap() {
		return sequenceInitMap;
	}

	public void setSequenceInitMap(Map<String, String> sequenceInitMap) {
		this.sequenceInitMap = sequenceInitMap;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}


}
