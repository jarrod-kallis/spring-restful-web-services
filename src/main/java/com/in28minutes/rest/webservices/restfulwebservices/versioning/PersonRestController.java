package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonRestController {
	// Versioning using the URL
	// http://localhost:8085/person/v1
	@GetMapping("/v1")
	public PersonV1 getUrlPersonV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping("/v2")
	public PersonV2 getUrlPersonV2() {
		return new PersonV2(new NameV1("Bob", "Charlie"));
	}

	// Versioning using URL params
	// http://localhost:8085/person?version=1
	@GetMapping(value = "", params = "version=1")
	public PersonV1 getParamPersonV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "", params = "version=2")
	public PersonV2 getParamPersonV2() {
		return new PersonV2(new NameV1("Bob", "Charlie"));
	}

	// Versioning using Header
	// http://localhost:8085/person
	// In the headers: Key=X-API-VERSION, Value=1
	@GetMapping(value = "", headers = "X-API-VERSION=1")
	public PersonV1 getHeaderPersonV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "", headers = "X-API-VERSION=2")
	public PersonV2 getHeaderPersonV2() {
		return new PersonV2(new NameV1("Bob", "Charlie"));
	}

	// Versioning using produces
	// http://localhost:8085/person
	// In the headers: Key=Accept, Value=application/v1+json
	@GetMapping(value = "", produces = "application/v1+json")
	public PersonV1 getProducesPersonV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "", produces = "application/v2+json")
	public PersonV2 getProducersPersonV2() {
		return new PersonV2(new NameV1("Bob", "Charlie"));
	}
}
