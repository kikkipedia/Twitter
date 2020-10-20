package com.kicki.backend.crud.controller;

import java.net.URI;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

import com.kicki.backend.crud.exception.ResourceNotFoundException;
import com.kicki.backend.crud.model.User;
import com.kicki.backend.crud.repository.UserRepository;
import com.kicki.backend.crud.repository.TweetsRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {
	private final UserRepository userRepository;
	private final TweetsRepository tweetsRepository;
	
	@Autowired
	public UserController(UserRepository userRepository, TweetsRepository tweetsRepository) {
		this.userRepository = userRepository;
		this.tweetsRepository = tweetsRepository;
	}
	
	@PostMapping("/add")
	public ResponseEntity<User> create(@Valid @RequestBody User user){
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).body(savedUser);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @Valid @RequestBody User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        user.setId(optionalUser.get().getId());
        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        userRepository.delete(optionalUser.get());

        return ResponseEntity.noContent().build();
    }
	
	@GetMapping("/all")
  	public Iterable<User> getAllUsers() {
  		return userRepository.findAll();
  	}
	
//	@GetMapping("/all/{id}")
//    public ResponseEntity<User> getById(@PathVariable Integer id) {
//        Optional<User> optionalUser = userRepository.findById(id);
//        if (!optionalUser.isPresent()) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//
//        return ResponseEntity.ok(optionalUser.get());
//    }

//    @GetMapping("/all")
//    public ResponseEntity<Page<User>> getAll(Pageable pageable) {
//        return ResponseEntity.ok(userRepository.findAll(pageable));
//    }

//	
	@GetMapping("/all/{id}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Integer userId)
		throws ResourceNotFoundException {
		User user = userRepository.findById(userId).orElseThrow(()
				-> new ResourceNotFoundException("User not found"));
		return ResponseEntity.ok().body(user);
	}
//	
//	
}