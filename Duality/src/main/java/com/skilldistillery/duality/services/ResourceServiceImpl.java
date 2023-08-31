package com.skilldistillery.duality.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.duality.entities.Resource;
import com.skilldistillery.duality.entities.User;
import com.skilldistillery.duality.repositories.ResourceRepository;
import com.skilldistillery.duality.repositories.UserRepository;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceRepository resourceRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Resource> listAllResources() {

		return resourceRepo.findAll();
	}

	@Override
	public List<Resource> listUserResources(int userId) {
		User user = null;
		Optional<User> userOpt = userRepo.findById(userId);
		if (userOpt != null) {
			user = userOpt.get();
			List<Resource> resources = resourceRepo.findByCreator_Id(user.getId());
			return resources;
		}

		return null;
	}

	@Override
	public Resource create(Resource newResource,String username) {	
		User user = userRepo.findByUsername(username);
		if(user !=null) {
			newResource.setCreator(user);
			return resourceRepo.saveAndFlush(newResource);
		}
		return null;
	}

	

}
