package com.neu.edu.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.edu.POJO.Payment;



public class PaymentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.equals(Payment.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Payment payment = (Payment)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"card","error.invalid.creditCardNumber","Credit Card Number Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"bankname","error.invalid.fullName","Full Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","error.invalid.expiration_month","Expiration Month Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"expmonth","error.invalid.expiration_year","Expiration year Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"expyear","error.invalid.expiration_month","Expiration Month Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"cvv","error.invalid.expiration_year","Expiration year Required");
	}
	
	
	

}
