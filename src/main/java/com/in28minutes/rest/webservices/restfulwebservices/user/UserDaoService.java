package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoService {
	private static List<User> users = new ArrayList<User>();
	private static int userId = 0;

	static {
		users.add(new User(++userId, "Adam", new Date()));
		users.add(new User(++userId, "Eve", new Date()));
		users.add(new User(++userId, "Jack", new Date()));
	}

	public List<User> getAll() {
		return users;
	}

	public User getById(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}

		return null;
	}

	public User create(User user) {
		user.setId(++userId);
		users.add(user);

		return user;
	}

	public User update(User user) {
		User currentUser = getById(user.getId());

		if (currentUser != null) {
			currentUser.update(user);
		}

		return currentUser;
	}

	public boolean delete(int id) {
		boolean result = false;

		for (int i = users.size() - 1; i >= 0; i--) {
			User user = users.get(i);

			if (user.getId() == id) {
				users.remove(i);
				result = true;
				break;
			}
		}

		return result;
	}
}
