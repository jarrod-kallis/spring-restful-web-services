package com.in28minutes.rest.webservices.restfulwebservices.post;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;

@JsonFilter("postFilter")
@Entity
public class Post {
	@Id
	@GeneratedValue
	private int id;

	private String message;
	// private int userId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	protected Post() {
		super();
	}

	public Post(int id, String message, User user) {
		super();
		this.id = id;
		this.message = message;
		// this.userId = userId;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// public int getUserId() {
	// return userId;
	// }
	//
	// public void setUserId(int userId) {
	// this.userId = userId;
	// }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return String.format("Post [id=%s, message=%s]", id, message);
	}

}
