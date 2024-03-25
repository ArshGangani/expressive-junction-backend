package com.blog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.app.entities.Post;
import com.blog.app.services.PostService;

@RestController
@RequestMapping("/blog")
public class PostRestController {
	private PostService postService;
	
	@Autowired
	public PostRestController(PostService thepostService) {
		postService = thepostService;
	}
	
	@GetMapping("/post")
	public List<Post> findAll() {
		return postService.findAll();
	}
	
	@GetMapping("/post/{postId}")
	public Post getPost(@PathVariable int postId) {
		Post post = postService.findById(postId);
		if(post == null) {
			throw new RuntimeException("post id not found:- " + postId);
		}
		return post;
	}
	
	@PostMapping("/post")
	public Post addPost(@RequestBody Post post) {
		postService.save(post);
		post = postService.findById(post.getId());
		return post;
	}
	
	@PutMapping("/post")
	public Post updatePost(@RequestBody Post post) {
		postService.save(post);
		post = postService.findById(post.getId());
		return post;
	}
	
	@DeleteMapping("/post/{postId}")
	public String deletePost(@PathVariable int postId) {
		Post tempPost = postService.findById(postId);
		
		if(tempPost==null) {
			throw new RuntimeException("Post id not found - " + postId); 
		}
		postService.deleteById(postId);
		return "Deleted Post id - " + postId;
	}
	
}
