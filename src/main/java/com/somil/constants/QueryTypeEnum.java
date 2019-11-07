package com.somil.constants;

import java.util.HashMap;
import java.util.Map;

public enum QueryTypeEnum {
	
	EQUAL("equal"),
	LIKE("like"),
	IN("in"),
	BETWEEN("between"),
	LESS("less"),
	GREATER("greater");
	
	private final String value;
	private static final Map<String, QueryTypeEnum> queryTypeToValueMap = new HashMap<String, QueryTypeEnum>();

	static {
		for (QueryTypeEnum myEnum : values()) {
			queryTypeToValueMap.put(myEnum.getValue(), myEnum);
		}
	}
	
	QueryTypeEnum(final String newValue) {
		value = newValue;
	}

	public String getValue() { 
		return value; 
	}

	public static QueryTypeEnum getQueryTypeById(String value) {
		return queryTypeToValueMap.get(value);
	}

}