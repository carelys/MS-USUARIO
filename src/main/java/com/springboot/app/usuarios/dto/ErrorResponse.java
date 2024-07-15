package com.springboot.app.usuarios.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ErrorResponse implements Serializable{
	private static final long serialVersionUID = 5202623950326428609L;
	
	private String message;
	
	public ErrorResponse(String message) {
		this.message = message;
		
	}

}
