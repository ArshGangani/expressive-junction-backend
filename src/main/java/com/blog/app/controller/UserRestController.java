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

import com.blog.app.entities.User;
import com.blog.app.services.UserService;

@RestController
@RequestMapping("/blog")
public class UserRestController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}

	@GetMapping("/user/{userId}")
	public User getUser(@PathVariable Long userId) {
		return userService.getUser(userId);
	}
		
	@PostMapping("/user")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	
	@PutMapping("/user/{userId}")
	public User updateUser(@PathVariable Long userId, @RequestBody User user) {
		return userService.updateUser(userId, user);
	}
		
	@DeleteMapping("/user/{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
	}
}
