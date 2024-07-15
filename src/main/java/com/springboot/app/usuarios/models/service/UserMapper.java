package com.springboot.app.usuarios.models.service;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.springboot.app.usuarios.dto.UserDto;
import com.springboot.app.usuarios.models.entity.User;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserDto userToUserDTO(User user);

	User userDTOToUser(UserDto userDTO);

}
