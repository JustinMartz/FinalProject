package com.skilldistillery.duality.services;

import java.util.List;

import com.skilldistillery.duality.entities.Comment;

public interface CommentService {
    
    List<Comment> listAllComments();
    
    Comment findById(int id);
    
    Comment createComment(Comment comment);
    
    Comment updateComment(int id, Comment comment);
    
    boolean deleteComment(int id);
}
