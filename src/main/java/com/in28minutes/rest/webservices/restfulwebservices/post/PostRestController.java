package com.in28minutes.rest.webservices.restfulwebservices.post;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users/{userId}")
public class PostRestController {
	@Autowired
	private PostDaoService service;

	@GetMapping("/posts")
	public List<Post> getPosts(@PathVariable int userId) {
		return service.getAll(userId);
	}

	@GetMapping("/posts/{id}")
	public Post getPost(@PathVariable int userId, @PathVariable int id) {
		Post post = service.getById(id);

		if (post == null) {
			throw new PostNotFoundException(id);
		}

		if (post.getUserId() != userId) {
			throw new PostUserCombinationNotFoundException(userId, id);
		}

		return post;
	}

	@PostMapping({ "/posts" })
	public ResponseEntity<Post> createPost(@PathVariable int userId, @RequestBody Post post) {
		if (post.getId() > 0) {
			Post existingPost = service.getById(post.getId());
			if (existingPost != null) {
				throw new PostAlreadyExistsException(existingPost);
			}
		}

		post.setUserId(userId);
		Post savedPost = service.create(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest() // "/users/{user_id}/posts"
				.path("/{id}") // append "/{id}" to the URL
				.buildAndExpand(savedPost.getId()) // Assign post id to URL placeholder
				.toUri();

		// In the header of the response you will see something like:
		// Location: http://localhost:8085/users/4/posts/1
		return ResponseEntity.created(location).build();
	}
}
