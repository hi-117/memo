package com.memo.app.common.enums;

public enum SearchMatch {
	PERFECT(1, "完全一致"),
	PARTIAL(2, "部分一致"),
	PREFIX(3, "前方一致"),
	BACKWARD(4, "後方一致")
	;
	
	
	private final int value;
	private final String name;
	
	
	SearchMatch(int value, String name){
		this.value = value;
		this.name = name;
	}


	public int getValue() {
		return value;
	}


	public String getName() {
		return name;
	}
	
}
