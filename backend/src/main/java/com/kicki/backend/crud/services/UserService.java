package com.kicki.backend.crud.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kicki.backend.crud.model.*;
import com.kicki.backend.crud.repository.*;

@Service
public class UserService {
	
	private UserRepository userRepository;
	private TweetsRepository tweetsRepository;
	
	public UserService(UserRepository userRepository, TweetsRepository tweetRepository) {
		this.userRepository = userRepository;
		this.tweetsRepository = tweetsRepository;
	}
	
	// Creates a new User with tweets-fk
	@Transactional
	public ResponseEntity<Object> addUser(User user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());
		newUser.setBio(user.getBio());
		newUser.setTweets(user.getTweets());
		User savedUser = userRepository.save(newUser);
		if(userRepository.findById(savedUser.getId()).isPresent()) {
			return ResponseEntity.accepted().body("Successfully created");
		}
		else 
			return ResponseEntity.unprocessableEntity().body("Failed to create user");
	}
	//Deletes user
	public ResponseEntity<Object> deleteUser(Integer id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
			if(userRepository.findById(id).isPresent()) {
				return ResponseEntity.unprocessableEntity().body("Failed to delete user");
			}
			else return ResponseEntity.ok().body("Deleted successfully");
		}
		else
			return ResponseEntity.unprocessableEntity().body("User not found");
	}

}
