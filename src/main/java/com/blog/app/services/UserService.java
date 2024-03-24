package com.blog.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.DAO.UserDAO;
import com.blog.app.entities.User;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Transactional
	public List<User> getAllUser() {
		return userDAO.getAllUser();
	}
	
	@Transactional
	public User getUser(Long userId) {
		User user = userDAO.getUser(userId);
		if (user == null) {
            throw new EntityNotFoundException("User with ID " + userId + " not found");
        }
        return user;
	}
	
	@Transactional
	public User addUser(User user) {
		try {
			return userDAO.addUser(user);
		}
		catch (EntityExistsException ex) {
	    	throw new EntityExistsException("user already exists: " + ex.getMessage());
		}
	}
	
	@Transactional
    public User updateUser(Long userId, User updatedUser) {
        User existingUser = userDAO.getUser(userId);
        if (existingUser == null) {
            throw new EntityNotFoundException("User with ID " + userId + " not found");
        }
        return userDAO.updateUser(userId, updatedUser);
    }
	
	@Transactional
	public void deleteUser(Long userId) {
		User existingUser = userDAO.getUser(userId);
		if (existingUser == null) {
            throw new EntityNotFoundException("user with ID " + userId + " not found");
        }
		userDAO.deleteUser(userId);
	}
}
