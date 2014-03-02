package com.amateur.persistence;

import com.amateur.domain.Sequence;

public interface SequenceMapper {

	Sequence getSequence(String name);



	void updateSequence(Sequence sequence);



	int insertSequence(Sequence sequence);
}