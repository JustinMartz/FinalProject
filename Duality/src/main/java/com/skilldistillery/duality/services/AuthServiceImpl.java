package com.skilldistillery.duality.services;

import java.time.LocalDate;

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
		newUser.setUsername(user.getUsername());
		newUser.setPassword(encrypted);
		newUser.setEnabled(true);
		newUser.setRole("user");
		user.setPassword(encrypted);
		userRepo.saveAndFlush(newUser);
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
