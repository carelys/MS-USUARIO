package com.springboot.app.usuarios.exception.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.app.usuarios.dto.ErrorResponse;
import com.springboot.app.usuarios.exception.EmailValidatorException;
import com.springboot.app.usuarios.exception.ResourceAlredyExistException;
import com.springboot.app.usuarios.exception.ResourceNotFoundexcEption;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundexcEption.class)
	public ResponseEntity<ErrorResponse> handlerResourceNotFountException(ResourceNotFoundexcEption ex){
		var errorResponse = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceAlredyExistException.class)
	public ResponseEntity<ErrorResponse> handlerResourceAlredyExistException(ResourceAlredyExistException ex){
		var errorResponse = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handlerGlobalException(Exception ex){
		var errorResponse = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handlerValidException(MethodArgumentNotValidException ex){
		HashMap<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error-> {
			var fieldMessage = error.getField();
			var errorMessage = error.getDefaultMessage();
			errors.put(fieldMessage, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(EmailValidatorException.class)
	public ResponseEntity<ErrorResponse> handlerGlobalException(EmailValidatorException ex){
		var errorResponse = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
