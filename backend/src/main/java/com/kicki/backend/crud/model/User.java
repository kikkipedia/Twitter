package com.kicki.backend.crud.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // makes a table of the class User
@Table (name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	private String bio;
	
	@OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Tweets> tweets = new HashSet<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
	public Set<Tweets> getTweets() {
		return tweets;
	}
	
	public void setTweets(Set<Tweets> tweets) {
		this.tweets = tweets;
		for (Tweets t : tweets) {
			t.setUser(this);
		}
	}
}