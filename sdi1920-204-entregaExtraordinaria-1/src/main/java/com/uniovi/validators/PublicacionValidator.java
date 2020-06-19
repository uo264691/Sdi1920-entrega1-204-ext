package com.uniovi.validators;

import com.uniovi.entities.Publicacion;
import com.uniovi.entities.User;
import com.uniovi.services.PublicacionService;
import com.uniovi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class PublicacionValidator implements Validator {
	@Autowired
	private PublicacionService publicacionService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Publicacion publicacion= (Publicacion) target;
		
		//System.out.println("\n\n"+user.getName()+" QQQQQQQQQ "+user.getPassword()+"\n\n");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
		
		
	}
}