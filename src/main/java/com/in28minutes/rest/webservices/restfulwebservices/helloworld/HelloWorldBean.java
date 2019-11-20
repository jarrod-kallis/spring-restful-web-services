package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {
	private String message;

	public HelloWorldBean(String message) {
		super();
		this.message = message;
	}

	// Getter is required in order to be able to return it in JSON format
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return String.format("HelloWorldBean [message=%s]", message);
	}
}
