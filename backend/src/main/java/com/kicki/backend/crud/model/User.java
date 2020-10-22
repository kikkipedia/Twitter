package com.kicki.backend.crud.model;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity // makes a table of the class User
@Table (name = "user")
public class User {
	
  	@Id // primary key
	@GeneratedValue(strategy = GenerationType.AUTO)
  	@Column(name="userId")
	private Integer id;
	
  	@Column(name="username")
	@NotNull
	private String username;
	
	@Column(name="password")
  	@NotNull
	private String password;

	@Column(name="bio")
	private String bio;
	
	@OneToMany (targetEntity = Tweet.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Tweet> tweets; // stores the list of tweets
	
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
			return tweets;
		}
	
		public void setTweets(List <Tweet> tweets) {
			this.tweets = tweets;
		}
}