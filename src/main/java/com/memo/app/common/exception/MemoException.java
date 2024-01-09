package com.memo.app.common.exception;

public class MemoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MemoException(){
		super();
	}
	
	public MemoException(String message){
		super(message);
	}
}
