package com.skilldistillery.duality.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.duality.entities.Post;
import com.skilldistillery.duality.repositories.PostRepository;
import com.skilldistillery.duality.repositories.UserRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public Post getPost(int id) {
		Post post = null;
		Optional<Post> postOpt = postRepo.findById(id);
		if (postOpt.isPresent()) {
			post = postOpt.get();
			if (!post.getActive()) {
				return null;
			}

		}

		return post;
	}

	@Override
	public List<Post> listAllPosts() {
		return postRepo.findAll();

	}

	@Override
	public Post create(Post newPost, String username) {
		User user = userRepo.findByUsername(username);
		return postRepo.saveAndFlush(newPost);
	}

	@Override
	public Post update(int postId, Post newPost) {
		Post existingPost = null;
		Optional<Post> existingOpt = postRepo.findById(postId);
		if (existingOpt.isPresent()) {
			existingPost = existingOpt.get();
			existingPost.setTitle(newPost.getTitle());
			existingPost.setActive(newPost.getActive());
			existingPost.setMessage(newPost.getMessage());
			existingPost.setPersonal(newPost.getPersonal());
			existingPost.setUsersWhoFlagged(newPost.getUsersWhoFlagged());
			existingPost.setComments(newPost.getComments());
			existingPost.setAnonymous(newPost.getAnonymous());
			postRepo.saveAndFlush(existingPost);
		}
		return existingPost;

	}

	@Override
	public boolean delete(int postId) {
		Post existingPost = null;
		Optional<Post> existingOpt = postRepo.findById(postId);
		if (existingOpt.isPresent()) {
			existingPost = existingOpt.get();
			existingPost.setActive(false);
			return true;
		}
		return false;
	}
}
