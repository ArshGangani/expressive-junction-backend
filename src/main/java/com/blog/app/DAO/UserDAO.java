package com.blog.app.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.blog.app.entities.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class UserDAO {
private EntityManager entityManager;
	
	@Autowired
	public UserDAO(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	public List<User> findAll() {

		// create a query
		TypedQuery<User> theQuery = 
				entityManager.createQuery("from User", User.class);
		
		// execute query and get result list
		List<User> user = theQuery.getResultList();
		
		// return the results		
		return user;
	}
	
	public User findById(int theId) {

		User user = 
				entityManager.find(User.class, theId);
		return user;
	}
	
	public void save(User user) {

		System.out.println("Before setting:-"+user.getId());
		
		// save or update the category
		User dbUser = entityManager.merge(user);
				
		// update with id from db ... so we can get generated id for save/insert
		// category.setId(dbCategory.getId());
		System.out.println("After setting:-"+user.getId());
		
	}
	
	public void deleteById(int theId) {

		// retrieve the category
        User user = entityManager.find(User.class, theId);
        entityManager.remove(user);
		
	}
}
