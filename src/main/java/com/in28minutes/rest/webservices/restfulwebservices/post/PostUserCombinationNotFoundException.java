package com.in28minutes.rest.webservices.restfulwebservices.post;

import com.in28minutes.rest.webservices.restfulwebservices.exception.NotFoundException;

public class PostUserCombinationNotFoundException extends NotFoundException {
	private static final long serialVersionUID = 1L;

	public PostUserCombinationNotFoundException(int userId, int postId) {
		super(String.format("User with id, %d, is not the author of post with id, %d", userId, postId));
	}
}
