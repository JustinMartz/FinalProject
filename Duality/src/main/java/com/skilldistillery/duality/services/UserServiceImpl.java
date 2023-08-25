package com.skilldistillery.duality.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.duality.entities.User;
import com.skilldistillery.duality.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User getLoggedInUser(String username) {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			user=null;
		}
		return user;
	}

}
