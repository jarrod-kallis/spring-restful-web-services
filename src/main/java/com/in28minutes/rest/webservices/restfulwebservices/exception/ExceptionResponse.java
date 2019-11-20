package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Return all response exceptions in a customised format
public class ExceptionResponse {
	private Date timestamp;
	private String message;
	private String details;

	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public Map<String, Object> createErrorAttributes() {
		Map<String, Object> errorAttributes = new HashMap<String, Object>();

		errorAttributes.put("timestamp", getTimestamp());
		errorAttributes.put("message", getMessage());
		errorAttributes.put("details", getDetails());

		return errorAttributes;
	}
}
