package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;

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

	@GetMapping({ "/hello-world/{name}" })
	public HelloWorldBean helloWorld(@PathVariable String name) {
		return new HelloWorldBean("Hello World, " + name);
	}

	@GetMapping({ "/hello-world-i18n/{name}" })
	public HelloWorldBean helloWorldInternationalised(
			@RequestHeader(name = "Accept-Language", required = false) Locale locale, @PathVariable String name) {
		return new HelloWorldBean(messageSource.getMessage("hello-world", null, locale) + ", " + name);
	}
}
