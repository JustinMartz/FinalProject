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
			user = null;
		}
		return user;
	}

	@Override
	public User getSingleUser(int id) {
		User user = null;
		Optional<User> userOpt = userRepo.findById(id);
		if (userOpt.isPresent()) {
			user = userOpt.get();
		}

		return user;
	}
	
	
	@Override
	public User updateUser( User user) {
		System.out.println("got to user service");
	    Optional<User> optUser = userRepo.findById(user.getId());	    
	    if (optUser.isPresent()) {
	        User managedUser = optUser.get();
	        managedUser.setUsername(user.getUsername());
	        managedUser.setAboutMe(user.getAboutMe());
	        managedUser.setFirstName(user.getFirstName());
	        managedUser.setLastName(user.getLastName());
	        managedUser.setDob(user.getDob());
	        managedUser.setPassword(user.getPassword());
	       
	        return userRepo.saveAndFlush(user);
	    }
	    return null;
	}

	

}
