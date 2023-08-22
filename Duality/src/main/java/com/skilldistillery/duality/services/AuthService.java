package com.skilldistillery.duality.services;

import com.skilldistillery.duality.entities.User;

public interface AuthService {
	public User register(User user);
	public User getUserByUsername(String username);
}
