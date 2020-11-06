package com.kicki.backend.crud.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "user")
public class User {
	
  	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	@Column(name="id")
	private Integer id;
	
  	@Column(name="username")
	//@NotNull
	private String username;
	
	@Column(name="password")
  	//@NotNull
	private String password;

	@Column(name="bio")
	private String bio;
	
	//@JsonManagedReference
	@OneToMany (targetEntity = Tweet.class, cascade = CascadeType.ALL)
	private List<Tweet> tweet; // stores the list of tweets
	
	// TODO: OneToMany - Following
	
	public User() {
		
	}
	
	public User(String username, String password, String bio) {
		super();
		this.username = username;
		this.password = password;
		this.bio = bio;
	}
	
	public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getBio() {
			return bio;
		}
		public void setBio(String bio) {
			this.bio = bio;
		}
		public List<Tweet> getTweets() {
			return tweet;
		}
	
		public void setTweets(List <Tweet> tweet) {
			this.tweet = tweet;
		}
}