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
import com.blog.app.entities.User;
import com.blog.app.services.PostService;
import com.blog.app.services.UserService;

@RestController
@RequestMapping("/blog")
public class PostRestController {
	@Autowired
    private UserService userService;
	
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
	
	@GetMapping("/post/category/{categoryid}")
	public List<Post> findByCategory(@PathVariable int categoryid) {
		return postService.findByCategory(categoryid);
	}
	
	@GetMapping("/post/user/{userid}")
	public List<Post> findByUser(@PathVariable int userid) {
		return postService.findByUser(userid);
	}
	
	@PostMapping("/post")
	public Post addPost(@RequestBody Post post) {
		if(post.getAuthor().getPassword()==null) {
			throw new RuntimeException("give password");
		}
		User user = userService.getUser(post.getAuthor().getId());
		if(user==null) {
			throw new RuntimeException("user not found ");
		} else if(!user.getPassword().equals(post.getAuthor().getPassword())) {
			throw new RuntimeException("Incorrect password");
		} else {
			postService.save(post);
			post = postService.findById(post.getId());
			return post;
		}
	}
	
	@PutMapping("/post/{postId}")
	public Post updatePost(@PathVariable int postId,@RequestBody Post post) {
		Post tempPost = postService.findById(postId);
		System.out.println(post);
		if(tempPost==null) {
			throw new RuntimeException("Post not found - " + postId); 
		}
		if(post.getAuthor()==null) {
			throw new RuntimeException("give user information");
		} else if(!post.getAuthor().getPassword().equals(tempPost.getAuthor().getPassword())) {
			throw new RuntimeException("Incorrect Password");
		}
		postService.save(post);
		post = postService.findById(post.getId());
		return post;
	}
	
	@DeleteMapping("/post/{postId}")
	public String deletePost(@PathVariable int postId,@RequestBody User user) {
		Post tempPost = postService.findById(postId);
		System.out.println(user);
		if(tempPost==null) {
			throw new RuntimeException("Post not found - " + postId); 
		}
		if(user.getPassword()==null) {
			throw new RuntimeException("give user information");
		} else if(!user.getPassword().equals(tempPost.getAuthor().getPassword())) {
			throw new RuntimeException("Incorrect Password");
		}
		postService.deleteById(postId);
		return "Deleted Post id - " + postId;
	}
	
}
