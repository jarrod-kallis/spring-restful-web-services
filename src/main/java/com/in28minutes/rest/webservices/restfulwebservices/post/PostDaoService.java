package com.in28minutes.rest.webservices.restfulwebservices.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class PostDaoService {
	private static List<Post> posts = new ArrayList<Post>();
	private static int postId = 0;

	static {
		posts.add(new Post(++postId, "My first post: UserId: 1", 1));
		posts.add(new Post(++postId, "My second post: UserId: 1", 1));
		posts.add(new Post(++postId, "My first post: UserId: 3", 3));
	}

	public List<Post> getAll(int userId) {
		List<Post> userPosts = new ArrayList<Post>();

		for (Post post : posts) {
			if (post.getUserId() == userId) {
				userPosts.add(post);
			}
		}
		return userPosts;
	}

	public Post getById(int id) {
		for (Post post : posts) {
			if (post.getId() == id) {
				return post;
			}
		}

		return null;
	}

	public Post create(Post post) {
		post.setId(++postId);
		posts.add(post);

		return post;
	}

	public boolean delete(int id) {
		boolean result = false;

		for (int i = posts.size() - 1; i >= 0; i--) {
			Post post = posts.get(i);

			if (post.getId() == id) {
				posts.remove(i);
				result = true;
				break;
			}
		}

		return result;
	}
}
