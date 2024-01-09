package com.memo.app.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.memo.app.common.exception.MemoException;

@ControllerAdvice
public class MemoControllerAdvice {
	
	public String handleMemoException(MemoException e, Model model) {
		return "/error";
	}
}
