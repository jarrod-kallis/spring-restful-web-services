package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.in28minutes.rest.webservices.restfulwebservices.post.Post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Something creative describing this User class")
// Don't display the password field in a GET response, but allow it to be set in
// a POST
@JsonIgnoreProperties(allowSetters = true, value = { "password" })
// JPA Managed Entity
@Entity
public class User {
	private static final String NAME_RESTRICTION = "User's name should be at least 2 characters";
	private static final String BIRTHDAY_RESTRICTION = "Birthday must be in the past";

	// JPA primary key that is generated by DB
	@Id
	@GeneratedValue
	private int id;

	@Size(min = 2, message = NAME_RESTRICTION)
	@ApiModelProperty(notes = NAME_RESTRICTION)
	private String name;
	@Past(message = BIRTHDAY_RESTRICTION)
	@ApiModelProperty(notes = BIRTHDAY_RESTRICTION)
	private Date dob;

	// Don't display this field in the REST GET response, but you also can't set
	// it in a POST
	// @JsonIgnore
	@JsonProperty("password")
	private String password;

	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	protected User() {
		super();
	}

	public User(int id, String name, Date dob, String password) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
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
