package com.springboot.app.usuarios.exception;

public class ResourceAlredyExistException extends RuntimeException {

	private static final long serialVersionUID = -2649895125585602691L;

	public ResourceAlredyExistException(String message) {
		super(message);
		
	}

}
