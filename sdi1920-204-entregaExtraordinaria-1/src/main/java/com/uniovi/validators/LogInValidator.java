package com.uniovi.validators;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class LogInValidator implements Validator {
	@Autowired
	private UsersService usersService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		System.out.println("\n\n"+user.getName()+" QQQQQQQQQ "+user.getPassword()+"\n\n");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Error.empty");
		
				
		if (usersService.getUserByUsername(user.getUsername()).getPassword() != user.getPassword()) {
			errors.rejectValue("username", "Error.login.incorrect.password");
		}
		
	}
}