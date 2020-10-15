package com.kicki.backend.crud.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kicki.backend.crud.model.User;
import com.kicki.backend.crud.repository.UserRepository;
import com.kicki.backend.crud.exception.ResourceNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {
	@Autowired // gets the bean userRepository
	private UserRepository userRepository;
	
	@GetMapping("/all")
	@CrossOrigin(origins = "http://localhost:3000")
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/all/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
		throws ResourceNotFoundException {
		User user = userRepository.findById(userId).orElseThrow(()
				-> new ResourceNotFoundException("User not found"));
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping("/add")
	@CrossOrigin(origins = "http://localhost:3000")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PutMapping("/all/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
			User user = userRepository.findById(userId).orElseThrow(()
					-> new ResourceNotFoundException("User not found"));
			user.setUsername(userDetails.getUsername());
			user.setPassword(userDetails.getPassword());
			user.setBio(userDetails.getBio());
			final User updatedUser = userRepository.save(user);
			return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
	throws ResourceNotFoundException {
		User user = userRepository.findById(userId).orElseThrow(()
				-> new ResourceNotFoundException("User not found"));
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	
}