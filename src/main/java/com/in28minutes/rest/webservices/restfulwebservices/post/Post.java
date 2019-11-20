package com.in28minutes.rest.webservices.restfulwebservices.post;

public class Post {
	private int id;
	private String message;
	private int userId;

	public Post(int id, String message, int userId) {
		super();
		this.id = id;
		this.message = message;
		this.userId = userId;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", message=" + message + ", userId=" + userId + "]";
	}
}
