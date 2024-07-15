package com.springboot.app.usuarios.models.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.springboot.app.usuarios.models.entity.User;

public interface UserDao extends CrudRepository<User, UUID>{
	
	public Optional<User> findById(UUID id);

	public Optional<User> findByEmail(String email);

}
