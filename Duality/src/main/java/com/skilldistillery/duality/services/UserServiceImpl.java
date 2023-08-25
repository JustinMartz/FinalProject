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
	public User getUser(String username, int id) {
		User user = null;
		if (userRepo.findByUsername(username) != null) {
			Optional<User> userOpt = userRepo.findById(id);
			if (userOpt.isPresent()) {
				user = userOpt.get();
			}
		}
		return user;
	}

}
