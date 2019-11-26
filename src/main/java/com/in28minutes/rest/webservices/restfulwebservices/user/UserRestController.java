package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.post.PostRestController;

@RestController
public class UserRestController {

	@Autowired
	private UserDaoService service;

	@Autowired
	private PostRestController postController;

	@GetMapping({ "/users" })
	public List<Resource<User>> getUsers() {
		List<User> users = service.getAll();
		List<Resource<User>> models = new ArrayList<Resource<User>>();

		for (User user : users) {
			models.add(addLinks(user));
		}

		return models;
	}

	@GetMapping("/users/{id}")
	public Resource<User> getUser(@PathVariable int id) {
		User user = service.getById(id);

		if (user == null) {
			throw new UserNotFoundException(id);
		}

		Resource<User> model = addLinks(user);

		return model;
	}

	private Resource<User> addLinks(User user) {
		// Add links to the returned User class
		Resource<User> model = new Resource<User>(user);

		ControllerLinkBuilder usersLink = linkTo(((UserRestController) methodOn(this.getClass())).getUsers());
		ControllerLinkBuilder postsLink = linkTo(
				((PostRestController) methodOn(postController.getClass())).getPosts(user.getId()));
		model.add(usersLink.withRel("users"));
		model.add(postsLink.withRel("posts"));

		return model;
	}

	@PostMapping({ "/users" })
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		if (user.getId() > 0) {
			User existingUser = service.getById(user.getId());
			if (existingUser != null) {
				throw new UserAlreadyExistsException(existingUser);
			}
		}

		User savedUser = service.create(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest() // "/users"
				.path("/{id}") // append "/{id}" to the URL
				.buildAndExpand(savedUser.getId()) // Assign user id to URL placeholder
				.toUri();

		// In the header of the response you will see something like:
		// Location: http://localhost:8085/users/4
		return ResponseEntity.created(location).build();
	}

	@PutMapping({ "/users" })
	public User updateUser(@RequestBody User user) {
		User updatedUser = null;

		if (user.getId() > 0) {
			updatedUser = service.update(user);
		}

		if (updatedUser == null) {
			throw new UserNotFoundException(user.getId());
		}

		return updatedUser;
	}

	@DeleteMapping("/users/{id}")
	public Object deleteUser(@PathVariable int id) {
		User deleteUser = service.getById(id);

		if (deleteUser == null) {
			throw new UserNotFoundException(id);
		}

		boolean deleteSuccess = service.delete(id);

		Object objResult = new Object() {
			User user = deleteUser;
			boolean success = deleteSuccess;

			@SuppressWarnings("unused")
			public User getUser() {
				return user;
			}

			@SuppressWarnings("unused")
			public boolean getSuccess() {
				return success;
			}
		};

		return objResult;
	}

}
