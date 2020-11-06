package com.kicki.backend.crud.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
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
		Map<String,String> response = new HashMap<String, String>();		
		if(userRepository.findById(savedUser.getId()).isPresent()) {
			response.put("ok", "success saving data");
			return ResponseEntity.accepted().body(response);
		}
		else 
			response.put("error", "Failed to create user");
			return ResponseEntity.unprocessableEntity().body(response);
	}
	//Deletes user
	public ResponseEntity<Object> deleteUser(Integer id) {
		Map<String,String> response = new HashMap<String, String>();
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
			if(userRepository.findById(id).isPresent()) {
				response.put("error", "Failed to delete user");
				return ResponseEntity.unprocessableEntity().body(response);
			}
			else 
				response.put("ok", "success deleting data");
				return ResponseEntity.ok().body(response);
		}
		else
			response.put("error", "user not found");
			return ResponseEntity.unprocessableEntity().body(response);
	}

}
