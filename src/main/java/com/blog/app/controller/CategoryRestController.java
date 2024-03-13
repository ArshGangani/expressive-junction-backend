package com.blog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.entities.Category;
import com.blog.app.services.CategoryService;


@RestController
@RequestMapping("/api")
public class CategoryRestController {

	private CategoryService categoryServices;
	
	@Autowired
	public CategoryRestController(CategoryService thecategoryServices) {
		categoryServices = thecategoryServices;
	}
	
	@GetMapping("/category")
	public List<Category> findAll() {
		return categoryServices.findAll();
	}
	
	@GetMapping("/category/{CategoryId}")
	public Category getCategory(@PathVariable int CategoryId) {
		
		Category thecategory = categoryServices.findById(CategoryId);
		
		if (thecategory == null) {
			throw new RuntimeException("Category id not found - " + CategoryId);
		}
		
		return thecategory;
	}
	
	@PutMapping("/category")
	public Category updateEmployee(@RequestBody Category thecategory) {
		
		categoryServices.save(thecategory);
		
		return thecategory;
	}
}
