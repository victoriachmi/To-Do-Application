package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.*;

@Service
public class UserService implements UserDetailsService {

	// CRUD

	private static List<User> users = new ArrayList<User>();

	private static int userCount = 4;

	static {
		users.add(new User(1, "Victoria", "vic", "mon", true));
		users.add(new User(1, "Patrick", "pat", "tues", true));
		users.add(new User(1, "Ashton", "ash", "wed", true));
		users.add(new User(1, "Jordan", "jord", "thurd", true));
	}

	public List<User> listUsers() {
		return users;
	}

	public User getUserByName(String userName) {
		for (User user : users) {
			if (user.getUserName().equals(userName)) {
				return user;
			}
		}
		return null;
	}

	public User getUser(int id) {
		for (User User : users) {
			if (User.getId() == id) {
				return User;
			}
		}
		return null;
	}

	public User addUser(String userName, String password, String fullname) {
		User newUser = new User(++userCount, userName, password, fullname, null);
		users.add(newUser);
		return newUser;
	}

	public User deleteUser(int userId) {
		User deleteUser = users.stream().filter(user -> user.getId() == userId).findFirst().orElse(null);
		users.remove(deleteUser);
		return deleteUser;
	}

	public User updateUser(User user) {
		deleteUser(user.getId());
		users.add(user);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = getUserByName(username);

		UserBuilder userBuilder = null;

		if (user != null) {

			userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
			userBuilder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
			userBuilder.roles("USER");

			return userBuilder.build();
		}

		else {
			throw new UsernameNotFoundException(" We can't find you!!!");
		}
	}
}
