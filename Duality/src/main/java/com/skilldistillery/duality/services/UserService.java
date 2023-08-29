package com.skilldistillery.duality.services;

import com.skilldistillery.duality.entities.User;

public interface UserService {
	public User getLoggedInUser(String username);

	User getSingleUser(int id);
}
