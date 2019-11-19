package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	// @RequestMapping(path = "/hello-world", method = RequestMethod.GET)
	// @GetMapping(path = "/hello-world")
	@GetMapping({ "/hello-world" })
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping({ "/hello-world-bean" })
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
}
