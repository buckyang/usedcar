package com.amateur.persistence;

import java.util.List;

import com.amateur.domain.SelectOption;

public interface SelectOptionMapper {
	List<SelectOption> getSelectionOptionsByName(String name);
}
