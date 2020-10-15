package com.kicki.backend.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // makes a table of the class User
@Table (name = "user")
public class User {
	private long id;
	private String username;
	private String password;
	private String bio;
	private Tweets tweets;
	
	public User() {
		
	}
	
	public User(String username, String password, String bio, Tweets tweets) {
		this.username = username;
		this.password = password;
		this.bio = bio;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		public long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "user_name", nullable = false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "pass_word", nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "bio", nullable = true)
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tweets_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public Tweets getTweets() {
		return tweets;
	}
	public void setTweets(Tweets tweets) {
		this.tweets = tweets;
	}
    

	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password +", bio=" + bio +"]";
	}
}