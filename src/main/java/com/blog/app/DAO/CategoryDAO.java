package com.blog.app.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.blog.app.entities.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class CategoryDAO {

	private EntityManager entityManager;
	
	@Autowired
	public CategoryDAO(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	public List<Category> findAll() {

		// create a query
		TypedQuery<Category> theQuery = 
				entityManager.createQuery("from Category", Category.class);
		
		// execute query and get result list
		List<Category> category = theQuery.getResultList();
		
		// return the results		
		return category;
	}
	
	public Category findById(int theId) {

		Category category = 
				entityManager.find(Category.class, theId);
		return category;
	}
	
	public void save(Category category) {

		System.out.println("Before setting:-"+category.getId());
		
		// save or update the category
		Category dbCategory = entityManager.merge(category);
				
		// update with id from db ... so we can get generated id for save/insert
		// category.setId(dbCategory.getId());
		
		 System.out.println("After setting:-"+category.getId());
		
	}
	
	public void deleteById(int theId) {

		// retrieve the category
        Category category = entityManager.find(Category.class, theId);
        entityManager.remove(category);
		
	}
	
	
}
