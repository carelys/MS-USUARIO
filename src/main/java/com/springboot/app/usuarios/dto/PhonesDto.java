package com.springboot.app.usuarios.dto;

import java.io.Serializable;
import java.util.UUID;

import com.springboot.app.usuarios.models.entity.User;

import lombok.Data;

@Data
public class PhonesDto implements Serializable{

	private static final long serialVersionUID = -1808304390618570628L;
	private UUID id;
	private String number;
	private String cityCode;
	private String contryCode;
	private User user;

}
