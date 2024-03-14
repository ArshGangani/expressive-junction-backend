package com.blog.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.blog.app.DAO.UserDAO;
import com.blog.app.entities.User;

import jakarta.transaction.Transactional;

@Service
public class UserService {
private UserDAO userDAO;
	
	@Autowired
	public UserService(UserDAO theUserDAO) {
		userDAO = theUserDAO;
	}
	
	@Transactional
	public List<User> findAll() {
		return userDAO.findAll();
	}
	
	@Transactional
	public User findById(int theId) {
		return userDAO.findById(theId);

	}
	
	@Transactional
	public void save(User theUser) {
		userDAO.save(theUser);

	}
	
	@Transactional
	public void deleteById(int theId) {
		userDAO.deleteById(theId);

	}
}
