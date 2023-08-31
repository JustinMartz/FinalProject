package com.skilldistillery.duality.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.duality.entities.User;
import com.skilldistillery.duality.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class UserController {

	@Autowired
	private UserService userServ;

	@GetMapping("users")
	public User getUser(Principal principal, HttpServletResponse response) {
		User user = userServ.getLoggedInUser(principal.getName());
		if (user != null) {
			response.setStatus(200);
		} else {
			response.setStatus(404);
		}
		return user;

	}

	@GetMapping("users/{id}")
	User getUserById(@PathVariable("id") int userId, HttpServletResponse res) {
		User user = userServ.getSingleUser(userId);
		if (user == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}

		return user;
	}
	
	
	@PutMapping("users/{id}")
	public User updateUser(Principal principal, @PathVariable("id") int userId, @RequestBody User user, HttpServletResponse res) {
	    try {
	        user = userServ.updateUser(userId, user);
	        if (user == null) {
	            res.setStatus(404);
	        } else {
	            res.setStatus(200);
	        }
	    } catch (Exception e) {
	        res.setStatus(400);
	        user = null;
	    }
	    return user;
	}


//	@PostMapping("user")
//	public User createUser(@RequestBody User user, HttpServletResponse res, HttpServletRequest req) {
//		System.out.println(user);
//		try {
//			user = userService.create(user);
//			res.setStatus(201);
//			StringBuffer url = req.getRequestURL();
//			url.append("/").append(user.getId());
//			res.setHeader("Location", url.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//			res.setStatus(400);
//			user = null;
//		}
//		return user;
//	}
//	@PutMapping("user/{userId}")
//	public User updateUser(@PathVariable int userId, @RequestBody User user, HttpServletResponse res) {
//		try {
//			user = userService.update(userId, user);
//			if (user == null) {
//				res.setStatus(404);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			res.setStatus(400);
//		}
//		return user;
//	}
//	
//	@DeleteMapping("user/{userId}")
//	public void deleteUser(@PathVariable("userId") Integer userId, HttpServletResponse res) {
//		try {
//			if (userService.delete(userId)) {
//				res.setStatus(200);
//			} else {
//				res.setStatus(404);
//			}
//		} catch (Exception e) {
//			res.setStatus(400);
//			e.printStackTrace();
//		}
//	}
}
