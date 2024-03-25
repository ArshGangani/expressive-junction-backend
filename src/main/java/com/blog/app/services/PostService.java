package com.blog.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.app.DAO.PostDAO;
import com.blog.app.entities.Post;

@Service
public class PostService {
	private PostDAO postDAO;
	
	@Autowired
	public PostService(PostDAO thepostDAO) {
		postDAO = thepostDAO;
	}
	
	@Transactional
	public List<Post> findAll() {
		return postDAO.findAll();
	}
	
	@Transactional
	public Post findById(int postId) {
		return postDAO.findById(postId);
	}
	
	@Transactional
	public Post save(Post thePost) {
		return postDAO.save(thePost);
	}
	
	@Transactional
	public void deleteById(int theId) {
		postDAO.deleteById(theId);
	}
}
