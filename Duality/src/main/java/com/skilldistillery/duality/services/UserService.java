package com.skilldistillery.duality.services;

import com.skilldistillery.duality.entities.User;

public interface UserService {
	public User getUser(String username, int id);
}
