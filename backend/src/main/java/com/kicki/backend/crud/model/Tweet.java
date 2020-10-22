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
	private User username;
	
	public Tweet() {
		
	}

	public Tweet(String tweet, User username) {
		this.tweet = tweet;
		this.username = username;
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
		return username;
	}

	public void setUser(User username) {
		this.username = username;
	}
	

}
