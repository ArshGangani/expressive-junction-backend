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

import com.blog.app.entities.Category;
import com.blog.app.services.CategoryService;

@RestController
@RequestMapping("/blog")
public class CategoryRestController {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/category")
	public List<Category> getAllCategory() {
		return categoryService.getAllCategory();
	}
	
	@GetMapping("/category/{CategoryId}")
	public Category getCategory(@PathVariable Long CategoryId) {	
		return categoryService.getCategory(CategoryId);
	}
	
	@PostMapping("/category")
	public Category addCategory(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}
	
	@PutMapping("/category/{CategoryId}")
	public Category updateCategory(@PathVariable Long CategoryId, @RequestBody Category category) {
		return categoryService.updateCategory(CategoryId, category);
	}
	
	@DeleteMapping("/category/{CategoryId}")
	public void deleteCategory(@PathVariable Long CategoryId) {
		categoryService.deleteCategory(CategoryId);
	}
}
