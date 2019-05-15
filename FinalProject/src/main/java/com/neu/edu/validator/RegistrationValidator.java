package com.neu.edu.validator;

import org.springframework.validation.*;

import com.neu.edu.POJO.CustomerDetails;



public class RegistrationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(CustomerDetails.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		 ValidationUtils.rejectIfEmpty(errors, "name", "empty-username", "Name cannot be blank");
	     ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty-password", "Email cannot be blank");

		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty-username", "Username cannot be blank");
	     ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty-password", "Password cannot be blank");

		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmpassword", "empty-username", "Confirm Password cannot be blank");
	     ValidationUtils.rejectIfEmpty(errors, "address", "empty-password", "Address cannot be blank");

		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNo", "empty-username", "Phone Number cannot be blank");
	     
			CustomerDetails cust = (CustomerDetails)target;
			
			if(!(cust.getPassword().equals(cust.getConfirmpassword()))){
				errors.rejectValue("password", "notmatch.password");
			}
	     
	     
	}
	
	

}
