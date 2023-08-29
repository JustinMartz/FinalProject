package com.skilldistillery.duality.controllers;

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

import com.skilldistillery.duality.entities.Comment;
import com.skilldistillery.duality.services.CommentService;

@RestController
@RequestMapping("api/comments/")
@CrossOrigin({"*", "http://localhost/"})
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping
	public List<Comment> listAllComments() {
		return commentService.listAllComments();
	}

	@GetMapping("{id}")
	public Comment getCommentById(@PathVariable int id, HttpServletResponse res) {
		Comment comment = commentService.findById(id);
		if (comment == null) {
			res.setStatus(404);
		}
		return comment;
	}

	@PostMapping
	public Comment createComment(@RequestBody Comment comment, HttpServletResponse res, HttpServletRequest req) {
		try {
			comment = commentService.createComment(comment);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(comment.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			comment = null;
		}
		return comment;
	}

	@PutMapping("{id}")
	public Comment updateComment(@PathVariable int id, @RequestBody Comment comment, HttpServletResponse res) {
		try {
			comment = commentService.updateComment(id, comment);
			if (comment == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			comment = null;
		}
		return comment;
	}

	@DeleteMapping("{id}")
	public void deleteComment(@PathVariable int id, HttpServletResponse res) {
		try {
			if (commentService.deleteComment(id)) {
				res.setStatus(200);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

	@GetMapping("posts/{postId}")
	public List<Comment> findCommentsByPostId(@PathVariable int postId, HttpServletResponse res) {
		List<Comment> comments = commentService.findCommentsByPostId(postId);
		if (comments == null) {
			res.setStatus(404);
			return null;
		} else {
			return comments;
		}
	}
}










