package com.springboot.app.usuarios.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.app.usuarios.models.entity.Phone;
import com.springboot.app.usuarios.models.entity.User;
import com.springboot.app.usuarios.models.service.IEmailValidatorService;
import com.springboot.app.usuarios.models.service.IUserService;
import com.springboot.app.usuarios.security.JwtTokenService;
import com.springboot.app.usuarios.security.TestSecurityConfig;

@WebMvcTest(UserController.class)
@Import(TestSecurityConfig.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IUserService userService;

	@MockBean
	private IEmailValidatorService emailValidatorService;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private JwtTokenService jwtTokenService;

	@Test
	public void findAllUser_ReturnOK() throws Exception {
		ArrayList<User> userList = new ArrayList<>();
		User user = new User();
		user.setId(new UUID(1L, 1L));
		user.setEmail(user.getEmail());
		userList.add(user);
		when(userService.findAll()).thenReturn(userList);

		mockMvc.perform(get("/users")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void findUserByID_ReturnOK() throws Exception {
		User user = new User();
		user.setId(new UUID(1L, 1L));
		user.setEmail(user.getEmail());
		Optional<User> userOptional = Optional.of(user);

		when(userService.findById(user.getId())).thenReturn(userOptional);

		mockMvc.perform(get("/users/{id}", user.getId())).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void createUser_ReturnOK() throws Exception {
		User user = new User();
		List<Phone> listPhone = new ArrayList<>();
		Phone phone = new Phone();
		phone.setId(new UUID(1L, 1L));
		listPhone.add(phone);
		user.setPhones(listPhone);
		Optional<User> userOptional = Optional.empty();
		user.setId(new UUID(1L, 1L));
		user.setEmail("test@gmail.com");
		

		when(emailValidatorService.isValid(user.getEmail())).thenReturn(true);
		when(userService.findByEmail(user.getEmail())).thenReturn(userOptional);
		when(userService.save(user, null)).thenReturn(user);

		mockMvc.perform(
				post("/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void createUser_ReturnConflict() throws Exception {
		User user = new User();
		List<Phone> listPhone = new ArrayList<>();
		Phone phone = new Phone();
		phone.setId(new UUID(1L, 1L));
		listPhone.add(phone);
		user.setPhones(listPhone);
		user.setId(new UUID(1L, 1L));
		user.setEmail("test@gmail.com");
		Optional<User> userOptional = Optional.of(user);

		when(emailValidatorService.isValid(user.getEmail())).thenReturn(true);
		when(userService.findByEmail(user.getEmail())).thenReturn(userOptional);

		mockMvc.perform(
				post("/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isConflict()).andDo(print());

	}

	@Test
	public void createUser_ReturnBadRequest() throws Exception {
		User user = new User();
		user.setId(new UUID(1L, 1L));
		user.setEmail("@gmail.com");
		List<Phone> listPhone = new ArrayList<>();
		Phone phone = new Phone();
		phone.setId(new UUID(1L, 1L));
		listPhone.add(phone);
		user.setPhones(listPhone);

		when(emailValidatorService.isValid(user.getEmail())).thenReturn(false);

		mockMvc.perform(
				post("/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isBadRequest()).andDo(print());

	}

	@Test
	public void updateUser_ReturnOk() throws Exception {
		User user = new User();
		user.setId(new UUID(1L, 1L));
		user.setEmail("test@gmail.com");
		Optional<User> userOptional = Optional.of(user);
		List<Phone> listPhone = new ArrayList<>();
		Phone phone = new Phone();
		phone.setId(new UUID(1L, 1L));
		listPhone.add(phone);
		user.setPhones(listPhone);

		when(userService.findById(user.getId())).thenReturn(userOptional);
		mockMvc.perform(put("/users/{id}", user.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user))).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void updateUser_ReturnIsNotFound() throws Exception {
		User user = new User();
		user.setId(new UUID(1L, 1L));
		user.setEmail("test@gmail.com");
		Optional<User> userOptional = Optional.empty();
		List<Phone> listPhone = new ArrayList<>();
		Phone phone = new Phone();
		phone.setId(new UUID(1L, 1L));
		listPhone.add(phone);
		user.setPhones(listPhone);

		when(userService.findById(user.getId())).thenReturn(userOptional);
		mockMvc.perform(put("/users/{id}", user.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user))).andExpect(status().isNotFound()).andDo(print());

	}

	@Test
	public void deleteUser_ReturnOk() throws Exception {
		User user = new User();
		Optional<User> userOptional = Optional.of(user);
		user.setId(new UUID(1L, 1L));

		when(userService.findById(user.getId())).thenReturn(userOptional);
		doNothing().when(userService).deleteById(user.getId());

		mockMvc.perform(delete("/users/{id}", user.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user))).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void deleteUser_ReturnIsNotFound() throws Exception {
		User user = new User();
		Optional<User> userOptional = Optional.empty();
		user.setId(new UUID(1L, 1L));

		when(userService.findById(user.getId())).thenReturn(userOptional);

		mockMvc.perform(delete("/users/{id}", user.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user))).andExpect(status().isNotFound()).andDo(print());

	}

}
