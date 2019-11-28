package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Something creative describing this User class")
public class User {
	private static final String NAME_RESTRICTION = "User's name should be at least 2 characters";
	private static final String BIRTHDAY_RESTRICTION = "Birthday must be in the past";

	private int id;
	@Size(min = 2, message = NAME_RESTRICTION)
	@ApiModelProperty(notes = NAME_RESTRICTION)
	private String name;
	@Past(message = BIRTHDAY_RESTRICTION)
	@ApiModelProperty(notes = BIRTHDAY_RESTRICTION)
	private Date dob;

	public User(int id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public void update(User user) {
		this.setName(user.getName());
		this.setDob(user.getDob());
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
}
