package com.memo.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {
	
	@Before("execution(* com.memo.app.controller.MemoController.index(..))")
	public void startLog(JoinPoint jp) {
		System.out.println(String.format("method start: %s", jp.getSignature()));
	}
	
	@After("execution(* com.memo.app.controller.MemoController.index(..))")
	public void endLog(JoinPoint jp) {
		System.out.println(String.format("method end: %s", jp.getSignature()));
	}

}
