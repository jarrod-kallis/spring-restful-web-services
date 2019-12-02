package com.in28minutes.rest.webservices.restfulwebservices.versioning;

public class PersonV2 {
	private NameV1 name;

	public PersonV2(NameV1 name) {
		super();
		this.name = name;
	}

	public NameV1 getName() {
		return name;
	}

	public void setName(NameV1 name) {
		this.name = name;
	}

}
