package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	// This reads in the language from the Accept-Language header of the request
//	@GetMapping({ "/hello-world-i18n/{name}" })
//	public HelloWorldBean helloWorldInternationalised(
//			@RequestHeader(name = "Accept-Language", required = false) Locale locale, @PathVariable String name) {
//		return new HelloWorldBean(messageSource.getMessage("hello-world", null, locale) + ", " + name);
//	}

	// This uses the AcceptHeaderLocaleResolver, which means we don't need to read
	// it in, in every method
	@GetMapping({ "/hello-world-i18n-auto/{name}" })
	public HelloWorldBean helloWorldInternationalisedAuto(@PathVariable String name) {
		return new HelloWorldBean(
				messageSource.getMessage("hello-world", null, LocaleContextHolder.getLocale()) + ", " + name);
	}

}
