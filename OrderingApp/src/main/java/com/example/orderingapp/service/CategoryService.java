package com.example.orderingapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.orderingapp.model.Category;
import com.example.orderingapp.repository.CategoryRepository;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
	}
	
	public Category getCategory(Long categoryId) {
		return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
	}
	
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public void deleteCategory(Long categoryId) {
		Category categoryToDelete = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
		categoryRepository.delete(categoryToDelete);
	}

	public void updateCategory(Long categoryId, String newName) {
		Category category = getCategory(categoryId);
		category.setName(newName);
	    categoryRepository.save(category);
	}
}
