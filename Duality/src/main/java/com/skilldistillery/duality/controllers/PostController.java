package com.skilldistillery.duality.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.duality.entities.Post;
import com.skilldistillery.duality.services.PostService;
import com.skilldistillery.duality.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class PostController {
	
	@Autowired
	private PostService postServ;
	
	@Autowired
	private UserService userServ;
	
	
	
	@GetMapping("posts")
	List<Post> listAllPosts(HttpServletResponse response,HttpServletRequest req) {
		return postServ.listAllPosts();
	}
	
	@GetMapping("posts/{postId}")
	public Post getPost(Principal principal, HttpServletResponse response, @PathVariable("postId") int postId) {
		Post post = postServ.getPost(postId);
		if (post != null) {
			response.setStatus(200);
		} else {
			response.setStatus(404);
		}
		return post;
		
	}
	@PostMapping("posts")
	public Post create(Principal principal,HttpServletRequest req, HttpServletResponse res, @RequestBody Post post) {
		post.setCreator(userServ.getLoggedInUser(principal.getName()));
		
		post = postServ.create(post);
		
		if (post == null) {
			res.setStatus(401);
		} else {
			res.setStatus(201);
			StringBuffer URL = req.getRequestURL();
			res.setHeader("Location", URL.append("/").append(post.getId()).toString());
		}
		return post;
	}
	@PutMapping("posts/{tid}")
	public Post update(HttpServletRequest req, HttpServletResponse res, @PathVariable int tid, @RequestBody Post post) {
		try {
			post = postServ.update(tid, post);
			if (post == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			post = null;
		}

		return post;

	}
	@DeleteMapping("posts/{tid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int tid) {
		if (postServ.delete(tid)) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
	}


}
