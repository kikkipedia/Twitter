package com.kicki.backend.crud.controller;
import com.kicki.backend.crud.repository.TweetsRepository;
import com.kicki.backend.crud.repository.UserRepository;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kicki.backend.crud.model.Tweets;
import com.kicki.backend.crud.model.User;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/tweets")
public class TweetsController {
	
	private final TweetsRepository tweetsRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public TweetsController(TweetsRepository tweetsRepository, UserRepository userRepository) {
		this.tweetsRepository = tweetsRepository;
		this.userRepository = userRepository;
	}
	
	@PostMapping
	public ResponseEntity<Tweets> create(@RequestBody @Valid Tweets tweets) {
        Optional<User> optionalUser = userRepository.findById(tweets.getUser().getId());
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        tweets.setUser(optionalUser.get());

        Tweets savedTweet = tweetsRepository.save(tweets);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedTweet.getId()).toUri();

        return ResponseEntity.created(location).body(savedTweet);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Tweets> update(@RequestBody @Valid Tweets tweets, @PathVariable Integer id) {
        Optional<User> optionalUser = userRepository.findById(tweets.getUser().getId());
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Tweets> optionalTweet = tweetsRepository.findById(id);
        if (!optionalTweet.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        tweets.setUser(optionalUser.get());
        tweets.setId(optionalTweet.get().getId());
        tweetsRepository.save(tweets);

        return ResponseEntity.noContent().build();
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Tweets> delete(@PathVariable Integer id) {
        Optional<Tweets> optionalTweet = tweetsRepository.findById(id);
        if (!optionalTweet.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        tweetsRepository.delete(optionalTweet.get());

        return ResponseEntity.noContent().build();
    }
	
	@GetMapping
    public ResponseEntity<Page<Tweets>> getAll(Pageable pageable) {
        return ResponseEntity.ok(tweetsRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tweets> getById(@PathVariable Integer id) {
        Optional<Tweets> optionalTweet = tweetsRepository.findById(id);
        if (!optionalTweet.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalTweet.get());
    }
	
//	@GetMapping("/users/{userId}/tweets")
//	public Page<Tweets> getAllTweetsByUserId(@PathVariable (value = "userId") Long userId, Pageable pageable) {
//		return tweetsRepository.findByUserId(userId, pageable);		
//	}
//	
//	@PostMapping("/users/{userId}/tweets")
//	public Optional<Tweets> createTweet(@PathVariable (value="userId") Long userId, @Valid @RequestBody Tweets tweets) {
//		return userRepository.findById(userId).map(user -> {
//			tweets.setUser(user);
//			return tweetsRepository.save(tweets);
//		});
//	}
	
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