package com.springboot.app.usuarios.exception;

public class EmailValidatorException extends RuntimeException{
	
	
	private static final long serialVersionUID = 2069371182887248784L;

	public EmailValidatorException(String message) {
		super(message);
		
	}

}
