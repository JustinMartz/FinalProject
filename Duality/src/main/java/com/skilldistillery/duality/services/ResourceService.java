package com.skilldistillery.duality.services;

import java.util.List;

import com.skilldistillery.duality.entities.Resource;

public interface ResourceService {
	
	List<Resource> listAllResources();

	List<Resource> listUserResources(int userId);
	
	

}
