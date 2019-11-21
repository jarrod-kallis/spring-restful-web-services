package com.in28minutes.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

//	@Bean
//	public LocaleResolver localeResolver() {
//		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//		localeResolver.setDefaultLocale(Locale.US);
//		return localeResolver;
//	}

	// Gets the locale from the Accept-Language header in the request
	@Bean
	public LocaleResolver acceptHeaderLocaleResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	// This can be set in application.properties, and then this method isn't
	// required
//	@Bean
//	public ResourceBundleMessageSource messageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//
//		messageSource.setBasename("messages");
//
//		return messageSource;
//	}
}
