package com.kicki.backend.crud.controller;

import com.kicki.backend.crud.repository.TweetsRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
	
	// TODO _ POST METHOD
}

