package com.springboot.app.usuarios.models.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.springboot.app.usuarios.models.entity.User;

import jakarta.servlet.http.HttpServletRequest;

public interface IUserService {
	
	public List<User> findAll();
	public Optional<User> findById(UUID id);
	public Optional<User> findByEmail(String id);
	public User save(User user, HttpServletRequest request);
	public void deleteById(UUID id);
	public User updateUser(User user, User userDB );

}
