package com.springboot.app.usuarios.models.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.springboot.app.usuarios.models.dao.UserDao;
import com.springboot.app.usuarios.models.entity.User;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> findAll() {
		var users = userDao.findAll();
		return (List<User>) users;
	}

	@Override
	public Optional<User> findById(UUID id) {
		return userDao.findById(id);
	}

	@Override
	public User save(User user, HttpServletRequest request) {
		user.setActive(true);
		user.setCreated(new Date());
		user.setLastLogin(new Date());
		user.setToken(request.getHeader(HttpHeaders.AUTHORIZATION));
		return userDao.save(user);

	}

	@Override
	public void deleteById(UUID id) {
		userDao.deleteById(id);

	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public User updateUser(User user, User userDB) {
		user.setId(userDB.getId());
		user.setModified(new Date());
		user.setCreated(userDB.getCreated());
		user.setLastLogin(new Date());
		user.setToken(userDB.getToken());
		user.setActive(true);
		return userDao.save(user);
	}

}
