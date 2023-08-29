package com.skilldistillery.duality.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.duality.entities.Comment;
import com.skilldistillery.duality.entities.Post;
import com.skilldistillery.duality.repositories.CommentRepository;
import com.skilldistillery.duality.repositories.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private PostRepository postRepo;

	@Override
	public List<Comment> listAllComments() {
		return commentRepo.findAll();
	}

	@Override
	public Comment findById(int id) {
		Optional<Comment> commentOpt = commentRepo.findById(id);
		if (commentOpt.isPresent()) {
			return commentOpt.get();
		}
		return null;
	}

	@Override
	public Comment createComment(Comment newComment) {
		return commentRepo.saveAndFlush(newComment);
	}

	@Override
	public Comment updateComment(int id, Comment newComment) {
		Comment existingComment = null;
		Optional<Comment> existingOpt = commentRepo.findById(id);
		if (existingOpt.isPresent()) {
			existingComment = existingOpt.get();
			existingComment.setBody(newComment.getBody());
			existingComment.setActive(newComment.getActive());
			existingComment.setFlagged(newComment.getFlagged());
			existingComment.setCommentor(newComment.getCommentor());
			existingComment.setPost(newComment.getPost());
			commentRepo.saveAndFlush(existingComment);
		}
		return existingComment;
	}

	@Override
	public boolean deleteComment(int id) {
		boolean deleted = false;
		Optional<Comment> toDeleteOpt = commentRepo.findById(id);
		if (toDeleteOpt.isPresent()) {
			commentRepo.delete(toDeleteOpt.get());
			deleted = true;
		}
		return deleted;
	}

	@Override
	public List<Comment> findCommentsByPostId(int postId) {
		return commentRepo.findByPost_Id(postId);

	}

}
