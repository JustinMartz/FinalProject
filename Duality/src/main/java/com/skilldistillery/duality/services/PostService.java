package com.skilldistillery.duality.services;

import java.util.List;

import com.skilldistillery.duality.entities.Post;


public interface PostService {
	
	List<Post> listAllPosts();
	
	Post getPost(int postId);
	
	Post create(Post newPost);

	Post update(int postId, Post newPost);

	boolean delete(int postId);

}
