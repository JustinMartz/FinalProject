package com.skilldistillery.duality.services;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.duality.entities.User;
import com.skilldistillery.duality.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User register(User user) {
		User newUser = new User();
		String encrypted = encoder.encode(user.getPassword());
		
		if (userRepo.findByUsername(user.getUsername()) != null) {
			return null;
		} else {
			newUser.setUsername(user.getUsername());
		}
		
		newUser.setPassword(encrypted);
		newUser.setEnabled(true);
		newUser.setRole("user");
		
		if (user.getEmail() != null) {
			newUser.setEmail(user.getEmail());		
		}
		
		if (user.getAvatar() != null) {
			newUser.setAvatar(user.getAvatar());			
		}
		
		if (user.getFirstName() != null) {
			newUser.setFirstName(user.getFirstName());
		}
		
		if (user.getLastName() != null) {
			newUser.setLastName(user.getLastName());
		}
		
		if (user.getDob() != null) {
			newUser.setDob(user.getDob());
		}
		
		userRepo.saveAndFlush(newUser);
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
