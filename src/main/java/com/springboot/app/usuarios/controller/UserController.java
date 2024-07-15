package com.springboot.app.usuarios.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.usuarios.exception.EmailValidatorException;
import com.springboot.app.usuarios.exception.ResourceAlredyExistException;
import com.springboot.app.usuarios.exception.ResourceNotFoundexcEption;
import com.springboot.app.usuarios.models.entity.User;
import com.springboot.app.usuarios.models.service.IEmailValidatorService;
import com.springboot.app.usuarios.models.service.IUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IEmailValidatorService emailValidatorService;

	@GetMapping
	public List<User> findAll() {
		return userService.findAll();

	}

	@GetMapping("/{userId}")
	public User findById(@PathVariable UUID userId) {
		return userService.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundexcEption("User not found with id " + userId));

	}

	@PostMapping
	public User save(@Valid HttpServletRequest request,  @RequestBody User user) {
		var isvalid = emailValidatorService.isValid(user.getEmail());
		if (isvalid) {
			var userByEmail = userService.findByEmail(user.getEmail()).orElse(null);
			if (userByEmail == null) {
				return userService.save(user, request);
			}
			throw new ResourceAlredyExistException("The email " + user.getEmail() + " already exists");
		}
		throw new EmailValidatorException("The email " + user.getEmail() + " must be valid");
	}

	@PutMapping("/{userId}")
	public User update(@PathVariable UUID userId, @RequestBody User user) {
		var userDB = userService.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundexcEption("User not found with id " + userId));
		return userService.updateUser(user, userDB);
		
	}

	@DeleteMapping("/{userId}")
	public void delete(@PathVariable UUID userId) {
		userService.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundexcEption("User not found with id " + userId));
		userService.deleteById(userId);

	}

}
