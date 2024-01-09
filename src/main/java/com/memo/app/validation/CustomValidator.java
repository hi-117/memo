package com.memo.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomValidator implements ConstraintValidator<Custom, String> {
	
	private String custom;
	
	@Override
	public void initialize(Custom custom) {
		this.custom = custom.custom();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.equals(this.custom)) {
			return true;
		} else {
			return false;
		}
	}
}
