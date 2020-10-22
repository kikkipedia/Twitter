package com.kicki.backend.crud.controller;

import com.kicki.backend.crud.repository.TweetsRepository;
import com.kicki.backend.crud.repository.UserRepository;

import java.util.List;
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
import com.kicki.backend.crud.exception.ResourceNotFoundException;
import com.kicki.backend.crud.model.Tweet;

@RestController
public class TweetsController {
	
	private TweetsRepository tweetsRepository;
	
	public TweetsController(TweetsRepository tweetsRepository) {
		this.tweetsRepository = tweetsRepository;
	}
	
	@GetMapping("/tweets/{id}")
	public Tweet getTweets(@PathVariable Integer id) {
		if(tweetsRepository.findById(id).isPresent())
			return tweetsRepository.findById(id).get();
		else return null;
	}
	@GetMapping("/tweets/all")
	public List<Tweet> getTweets() {
		return tweetsRepository.findAll();
	}
}

// Gamla - funkade ej
/*
 * @RestController
 * 
 * @CrossOrigin(origins = "http://localhost:3000")
 * 
 * @RequestMapping("/tweets") public class TweetsController {
 * 
 * @Autowired private TweetsRepository tweetsRepository; private UserRepository
 * userRepository;
 * 
 * 
 * @GetMapping("/users/{userId}/tweets") public List <Tweets>
 * getTweetsByUser(@PathVariable(value="postId") Integer userId) { return
 * tweetsRepository.findByUserId(userId);
 * 
 * //}
 * 
 * @PostMapping("/users/{userId}/tweets/add") public Tweets
 * createTweets(@PathVariable(value = "userId") Integer userId,
 * 
 * @Valid @RequestBody Tweets tweets) throws ResourceNotFoundException { return
 * userRepository.findById(userId).map(user -> { tweets.setUser(user); return
 * tweetsRepository.save(tweets); }).orElseThrow(() -> new
 * ResourceNotFoundException("instructor not found")); }
 * 
 * @PutMapping("/users/{userId}/tweets/{tweetsId}") public Tweets
 * updateTweets(@PathVariable(value="userId") Integer userId,
 * 
 * @PathVariable(value="tweetsId") Integer tweetsId, @Valid @RequestBody Tweets
 * tweetsRequest) throws ResourceNotFoundException { if
 * (!userRepository.existsById(userId)) { throw new
 * ResourceNotFoundException("user id not found"); } return
 * tweetsRepository.findById(tweetsId).map(tweets -> {
 * tweets.setTweet(tweetsRequest.getTweet()); return
 * tweetsRepository.save(tweets); }).orElseThrow(() -> new
 * ResourceNotFoundException("tweet id not found")); }
 * 
 * 
 * @DeleteMapping("/users/{userId}/tweets/{tweetsId}") public ResponseEntity < ?
 * > deleteTweets(@PathVariable(value = "userId") Integer userId,
 * 
 * @PathVariable(value = "tweetsId") Integer tweetsId) throws
 * ResourceNotFoundException { return
 * tweetsRepository.findByIdAndUserId(tweetsId, userId).map(tweets -> {
 * tweetsRepository.delete(tweets); return ResponseEntity.ok().build();
 * }).orElseThrow(() -> new ResourceNotFoundException(
 * "Tweet not found with id " + tweetsId + " and userId " + userId)); }
 * 
 * 
 * }
 */