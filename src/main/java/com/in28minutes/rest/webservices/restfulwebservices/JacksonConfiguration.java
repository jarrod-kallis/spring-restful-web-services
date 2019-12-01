package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Configuration
public class JacksonConfiguration {

	public JacksonConfiguration(ObjectMapper objectMapper) {
		// Prevents an error being thrown if the rest controller responds without making
		// use of the filter on the class
		// See @JsonFilter on Post class, and getPosts on PostRestController
		objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
	}

}
