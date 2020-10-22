package com.kicki.backend.crud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.kicki.backend.crud.exception.ResourceNotFoundException;
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

// GAMLA _ FUNGERAR
/*
 * @RestController
 * 
 * @CrossOrigin(origins = "http://localhost:3000")
 * 
 * @RequestMapping("/users") public class UserController {
 * 
 * @Autowired private UserRepository userRepository;
 * 
 * @GetMapping("/all") public List<User> getUsers() { return
 * userRepository.findAll(); }
 * 
 * @GetMapping("/{userId}") public ResponseEntity< User > getUserById(
 * 
 * @PathVariable (value="userId") Integer userId) throws
 * ResourceNotFoundException { User user = userRepository.findById(userId)
 * .orElseThrow(() -> new ResourceNotFoundException("User not found: " +
 * userId)); return ResponseEntity.ok().body(user);
 * 
 * }
 * 
 * @PostMapping("/add") public User createUser(@Valid @RequestBody User user) {
 * return userRepository.save(user); }
 * 
 * @PutMapping("/{userId}") public ResponseEntity<User>
 * updateUser(@PathVariable(value="userId") Integer userId, @Valid @RequestBody
 * User userDetails) throws ResourceNotFoundException { User user =
 * userRepository.findById(userId) .orElseThrow(() -> new
 * ResourceNotFoundException("User not found"));
 * user.setUsername(userDetails.getUsername());
 * user.setPassword(userDetails.getPassword()); final User updatedUser =
 * userRepository.save(user); return ResponseEntity.ok(updatedUser); }
 * 
 * @DeleteMapping("/{userId}") public Map < String, Boolean > deleteUser(
 * 
 * @PathVariable(value = "userId") Integer userId) throws
 * ResourceNotFoundException { User user = userRepository.findById(userId)
 * .orElseThrow(() -> new ResourceNotFoundException("User not found :: " +
 * userId));
 * 
 * userRepository.delete(user); Map < String, Boolean > response = new HashMap <
 * > (); response.put("deleted", Boolean.TRUE); return response; }
 * 
 * }
 */