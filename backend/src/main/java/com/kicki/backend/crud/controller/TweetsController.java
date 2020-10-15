package com.kicki.backend.crud.controller;
import com.kicki.backend.crud.repository.TweetsRepository;
import com.kicki.backend.crud.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.kicki.backend.crud.exception.ResourceNotFoundException;
import com.kicki.backend.crud.model.Tweets;
import com.kicki.backend.crud.model.User;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/tweets")
public class TweetsController {
	
	@Autowired
	private TweetsRepository tweetsRepository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users/{userId}/tweets")
	public Page<Tweets> getAllTweetsByUserId(@PathVariable (value = "userId") Long userId, Pageable pageable) {
		return tweetsRepository.findByUserId(userId, pageable);		
	}
	
	@PostMapping("/users/{userId}/tweets")
	public Optional<Tweets> createTweet(@PathVariable (value="userId") Long userId, @Valid @RequestBody Tweets tweets) {
		return userRepository.findById(userId).map(user -> {
			tweets.setUser(user);
			return tweetsRepository.save(tweets);
		});
	}
	
//	@PutMapping("/users/tweets/{id}")
//	public ResponseEntity<Tweets> updateTweet(@PathVariable(value = "id") Long tweetsId,
//			@Valid @RequestBody Tweets userTweet) throws ResourceNotFoundException {
//			Tweets tweets = tweetsRepository.findById(tweetsId).orElseThrow(()
//					-> new ResourceNotFoundException("Tweet not found"));
//			tweets.setTweets(tweetsDetails.getTweet());
//			final Tweets updatedTweet = tweetsRepository.save(tweets);
//			return ResponseEntity.ok(updatedTweets);
//	}
//	
//	@DeleteMapping("/users/{id}")
//	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
//	throws ResourceNotFoundException {
//		User user = userRepository.findById(userId).orElseThrow(()
//				-> new ResourceNotFoundException("User not found"));
//		userRepository.delete(user);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
//		return response;
//	}
}