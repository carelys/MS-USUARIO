package com.springboot.app.usuarios.models.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailValidatorServiceImpl implements IEmailValidatorService {

	private String emailRegex;
	private Pattern pattern;

	public EmailValidatorServiceImpl(@Value("${email.regex}") String emailRegex) {
		this.emailRegex = emailRegex;
	}


	public boolean isValid(String email) {
		pattern = Pattern.compile(emailRegex);
		if (email == null || emailRegex == null) {
			return false;
		}
		return pattern.matcher(email).matches();
	}

}
