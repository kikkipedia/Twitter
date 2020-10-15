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

@Entity
@Table(name = "tweets")
public class Tweets extends Audit {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5059664177161641655L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "tweet", nullable = false)
	private String tweet;
	public String getTweet() {
		return tweet;
	}
	public void setUsername(String tweet) {
		this.tweet = tweet;
	}
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
	@Override
	public String toString() {
		return "Tweets [id=" + id + ", tweet=" + tweet +"]";
	}

}
