package com.in28minutes.rest.webservices.restfulwebservices.post;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserRestController;

@RestController
@RequestMapping("/users/{userId}")
public class PostRestController {
	@Autowired
	private PostDaoService service;

	@Autowired
	private PostRepository repo;

	@Autowired
	private UserRestController userController;

	@GetMapping("/posts")
	public List<Resource<Post>> getPosts(@PathVariable int userId) {
		// List<Post> posts = service.getAll(userId);
		Resource<User> userResource = userController.getUser(userId);
		List<Post> posts = userResource.getContent().getPosts();

		List<Resource<Post>> models = new ArrayList<Resource<Post>>();

		for (Post post : posts) {
			models.add(addLinks(post));
		}

		// Don't return certain fields for this request
		// SimpleBeanPropertyFilter propertyFilter =
		// SimpleBeanPropertyFilter.filterOutAllExcept("message", "id");
		// FilterProvider filter = new
		// SimpleFilterProvider().addFilter("postFilter", propertyFilter);
		// MappingJacksonValue mapping = new MappingJacksonValue(models);
		// mapping.setFilters(filter);

		return models;
	}

	@GetMapping("/posts/{id}")
	public MappingJacksonValue getPost(@PathVariable int userId, @PathVariable int id) {
		// Post post = service.getById(id);
		Optional<Post> postWrapper = repo.findById(id);

		// if (post == null) {
		// throw new PostNotFoundException(id);
		// }
		if (postWrapper.isPresent() == false) {
			throw new PostNotFoundException(id);
		}

		Post post = postWrapper.get();

		// if (post.getUserId() != userId) {
		// throw new PostUserCombinationNotFoundException(userId, id);
		// }
		if (post.getUser().getId() != userId) {
			throw new PostUserCombinationNotFoundException(userId, id);
		}

		Resource<Post> model = addLinks(post);

		// Don't return certain fields for this request
		SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("message");
		FilterProvider filter = new SimpleFilterProvider().addFilter("postFilter", propertyFilter);
		MappingJacksonValue mapping = new MappingJacksonValue(model);
		mapping.setFilters(filter);

		return mapping;
	}

	@PostMapping({ "/posts" })
	public ResponseEntity<Post> createPost(@PathVariable int userId, @RequestBody Post post) {
		if (post.getId() > 0) {
			// Post existingPost = service.getById(post.getId());
			Optional<Post> postWrapper = repo.findById(post.getId());
			// if (existingPost != null) {
			// throw new PostAlreadyExistsException(existingPost);
			// }
			if (postWrapper.isPresent() == true) {
				throw new PostAlreadyExistsException(postWrapper.get());
			}
		}

		Resource<User> user = userController.getUser(userId);
		post.setUser(user.getContent());
		// post.setUserId(userId);

		// Post savedPost = service.create(post);
		Post savedPost = repo.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest() // "/users/{user_id}/posts"
				.path("/{id}") // append "/{id}" to the URL
				.buildAndExpand(savedPost.getId()) // Assign post id to URL
													// placeholder
				.toUri();

		// In the header of the response you will see something like:
		// Location: http://localhost:8085/users/4/posts/1
		return ResponseEntity.created(location).build();
	}

	private Resource<Post> addLinks(Post post) {
		// Add links to the returned Post class
		Resource<Post> model = new Resource<Post>(post);

		// ControllerLinkBuilder postsLink = linkTo(
		// ((PostRestController)
		// methodOn(this.getClass())).getPosts(post.getUserId()));
		// ControllerLinkBuilder userLink = linkTo(
		// ((UserRestController)
		// methodOn(userController.getClass())).getUser(post.getUserId()));
		ControllerLinkBuilder postsLink = linkTo(
				((PostRestController) methodOn(this.getClass())).getPosts(post.getUser().getId()));
		ControllerLinkBuilder userLink = linkTo(
				((UserRestController) methodOn(userController.getClass())).getUser(post.getUser().getId()));
		model.add(postsLink.withRel("posts"));
		model.add(userLink.withRel("user"));

		return model;
	}
}
