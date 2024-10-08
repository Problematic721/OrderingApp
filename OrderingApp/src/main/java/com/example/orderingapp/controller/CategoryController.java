package com.example.orderingapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.orderingapp.model.Category;
import com.example.orderingapp.service.CategoryService;

import jakarta.validation.Valid;

@Controller
public class CategoryController {
	
	private final CategoryService categoryService;
	
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
	
	@GetMapping("/categories")
    public String viewCategories(Model model) {
		model.addAttribute("activePage", "categories");
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("newCategory" , new Category());
		model.addAttribute("categories", categories);
		return "categories";
	}
	
	@PostMapping("/categories/add")
    public String addCategory(@Valid @ModelAttribute Category category, BindingResult result) {
		if (result.hasErrors()) {
			return "categories";
		}
		categoryService.addCategory(category);
		return "redirect:/categories";
	}
	
	@GetMapping("/categories/remove/{categoryId}")
	public String removeCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return "redirect:/categories";
    }
	
	@PostMapping("/categories/edit/{categoryId}")
	public String editCategory(@PathVariable Long categoryId, @RequestParam String name) {
	    categoryService.updateCategory(categoryId, name);
	    return "redirect:/categories";
	}
}
