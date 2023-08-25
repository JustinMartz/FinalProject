//package com.skilldistillery.duality.controllers;
//
//import java.security.Principal;
//import java.util.List;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.skilldistillery.duality.entities.Post;
//import com.skilldistillery.duality.services.PostService;
//
//@RestController
//@RequestMapping("api")
//@CrossOrigin({"*", "http://localhost/"})
//public class PostController {
//	
//	@Autowired
//	private PostService postServ;
//	
//	
//	
//	@GetMapping("post")
//	List<Post> listPost(HttpServletResponse response) {
//		List<Post>posts = null;
//	}
//	
//	@GetMapping("post/{postId}")
//	public Post getPost(Principal principal, HttpServletResponse response, @PathVariable("postId") int postId) {
//		Post post = postServ.getPost(postId);
//		if (post != null) {
//			response.setStatus(200);
//		} else {
//			response.setStatus(404);
//		}
//		return post;
//		
//	}
//	
//	
//
//
//}
