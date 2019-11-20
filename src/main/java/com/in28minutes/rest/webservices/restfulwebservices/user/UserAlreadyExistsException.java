package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.in28minutes.rest.webservices.restfulwebservices.exception.AlreadyExistsException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends AlreadyExistsException {
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(User user) {
		super("User already exists: " + user);
	}
}
