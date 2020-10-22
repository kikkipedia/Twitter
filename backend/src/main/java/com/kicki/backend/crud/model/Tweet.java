package com.kicki.backend.crud.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table (name = "tweets")
public class Tweet extends Audit {

	private static final long serialVersionUID = 2455903507187330507L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="tweetsId")
	private Integer id;
	
	@Column(name="tweet")
	private String tweet;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	public Tweet() {
		
	}

	public Tweet(String tweet, User user) {
		this.tweet = tweet;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
