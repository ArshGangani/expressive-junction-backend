package com.blog.app.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.app.entities.User;
import jakarta.persistence.EntityManager;

@Repository
public class UserDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	public List<User> getAllUser() {
		return entityManager.createQuery("from User", User.class).getResultList();
	}
	
	public User getUser(Long userId) {
		return entityManager.find(User.class, userId);
	}
	
	// Add & also Update User
	public User addUser(User user) {
		entityManager.merge(user);
		return user;
	}
	
	public User updateUser(Long userId, User updatedUser) {
		updatedUser.setId(userId);
        return entityManager.merge(updatedUser);
	}
	
	public void deleteUser(Long userId) {
		User user = entityManager.find(User.class, userId);
        entityManager.remove(user);
	}
}
