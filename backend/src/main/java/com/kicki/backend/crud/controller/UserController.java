package com.kicki.backend.crud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.kicki.backend.crud.model.User;
import com.kicki.backend.crud.repository.UserRepository;
import com.kicki.backend.crud.services.UserService;

@RestController
public class UserController {
	private UserService userService;
	private UserRepository userRepository;
	
	public UserController(UserService userService, UserRepository userRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
	}
	
	@PostMapping("/users/add")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	@DeleteMapping("/users/delete/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
		return userService.deleteUser(id);
	}
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Integer id) {
		if(userRepository.findById(id).isPresent())
			return userRepository.findById(id).get();
		else return null;
	}
	@GetMapping("/users/all")
	public List <User> getUsers() {
		return userRepository.findAll();
	}
}

