package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(int userId) {
		super("User not found with id: " + userId);
	}
}
