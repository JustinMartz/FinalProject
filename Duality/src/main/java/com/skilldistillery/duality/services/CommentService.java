package com.skilldistillery.duality.services;

import java.util.List;

import com.skilldistillery.duality.entities.Comment;

public interface CommentService {

	List<Comment> listAllComments();

	Comment findById(int id);

	Comment createComment(Comment comment);

	Comment updateComment(int id, Comment newComment);

	boolean deleteComment(int id);

	List<Comment> findCommentsByPostId(int postId);
	
	Comment addCommentToPost(int postId, Comment comment);
}
