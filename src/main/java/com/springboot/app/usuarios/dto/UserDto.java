package com.springboot.app.usuarios.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class UserDto implements Serializable{

	private static final long serialVersionUID = -5130366145378436027L;
	private UUID id;
	private String name;
	private String email;
	private String password;
	private List<PhonesDto> phones;
	private Date created;
	private Date modified;
	private Date lastLogin;
	private String token;
	private boolean isActive;

}
