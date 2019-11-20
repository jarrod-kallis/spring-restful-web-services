package com.in28minutes.rest.webservices.restfulwebservices.post;

import com.in28minutes.rest.webservices.restfulwebservices.exception.NotFoundException;

public class PostNotFoundException extends NotFoundException {
	private static final long serialVersionUID = 1L;

	public PostNotFoundException(int postId) {
		super("Post not found with id: " + postId);
	}
}
