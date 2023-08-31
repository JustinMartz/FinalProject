package com.skilldistillery.duality.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.duality.entities.Comment;
import com.skilldistillery.duality.entities.Resource;
import com.skilldistillery.duality.services.ResourceService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class ResourceController {

	@Autowired
	ResourceService resourceServe;
	
	

	@GetMapping("resources")
	List<Resource> listAllResources(HttpServletResponse response, HttpServletRequest req) {
		List<Resource> resources = resourceServe.listAllResources();

		if (resources == null) {
			response.setStatus(404);
		} else {
			response.setStatus(200);
		}
		return resources;
	}

	@GetMapping("resources/{userId}")
	List<Resource> listAllUserResources(@PathVariable int userId, HttpServletResponse response, HttpServletRequest req) {

		List<Resource> resources = resourceServe.listUserResources(userId);

		if (resources == null) {
			response.setStatus(404);
		} else {
			response.setStatus(200);
		}
		return resources;
	}
	@PostMapping("resources")
	public Resource addResourceToUser(Principal principal,HttpServletRequest req, HttpServletResponse res, @RequestBody Resource resource) {
	
		resource = resourceServe.create(resource, principal.getName());
	if (resource == null) {
		res.setStatus(404);
	} else {
		res.setStatus(200);
	}
	return resource;
	}
}

