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
	private UserService userService;
	
	@Autowired
	public UserRestController(UserService theUserService) {
		userService = theUserService;
	}
	

	@GetMapping("/user")
	public List<User> findAll() {
		return userService.findAll();
	}

		
	@GetMapping("/user/{userId}")
	public User getUser(@PathVariable int userId) {
		
		User theUser = userService.findById(userId);
		
		if (theUser == null) {
			throw new RuntimeException("User id not found - " + userId);
		}
		
		return theUser;
	}
		
		
	@PostMapping("/user")
	public User addUser(@RequestBody User theUser) {
		
		userService.save(theUser);
		
		return theUser;
	}
	
	@PutMapping("/user")
	public User updateUser(@RequestBody User theUser) {
		
		userService.save(theUser);
		
		return theUser;
	}
		
	@DeleteMapping("/user/{userId}")
	public String deleteUser(@PathVariable int userId) {
		
		User theUser = userService.findById(userId);
		
		if (theUser == null) {
			throw new RuntimeException("Employee id not found - " + userId);
		}
		
		userService.deleteById(userId);
		
		return "Deleted employee id - " + userId;
	}
}
