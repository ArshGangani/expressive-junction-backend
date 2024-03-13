package com.blog.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.app.DAO.CategoryDAO;
import com.blog.app.entities.Category;

@Service
public class CategoryService {
	private CategoryDAO categoryDAO;
	
	@Autowired
	public CategoryService(CategoryDAO theCategoryDAO) {
		categoryDAO = theCategoryDAO;
	}

	@Transactional
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDAO.findAll();
	}

	@Transactional
	public Category findById(int employeeId) {
		// TODO Auto-generated method stub
		return categoryDAO.findById(employeeId);
	}

	@Transactional
	public void save(Category theEmployee) {
		// TODO Auto-generated method stub
		categoryDAO.save(theEmployee);
		
	}
	
	@Transactional
	public void deleteById(int theId) {
		categoryDAO.deleteById(theId);
	}


}