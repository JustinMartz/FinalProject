//package com.skilldistillery.duality.services;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.skilldistillery.duality.entities.Post;
//import com.skilldistillery.duality.repositories.PostRepository;
//@Service
//public class PostServiceImpl implements PostService {
//
//	
//	@Autowired
//	private PostRepository postRepo;
//
//	@Override
//	public Post getPost(int id) {
//		Post post = null;
//	Optional<Post> postOpt = postRepo.findById(id);
//		if (postOpt.isPresent()) {
//			post = postOpt.get();
//			return post;
//		}
//
//		return post;
//	}
//
//	@Override
//	public List<Post> listAllPosts() {
//		return postRepo
//		
//	}
//
//	@Override
//	public Post create(Post newPost) {
//	
//		return postRepo.saveAndFlush(newPost);
//	}
//
//	@Override
//	public Post update(int postId, Post newPost) {
//
//		return null;
//	}
//
//	@Override
//	public boolean delete(int postId) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}
