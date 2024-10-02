package com.example.orderingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.orderingapp.model.Category;
import com.example.orderingapp.service.CategoryService;

import jakarta.validation.Valid;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
    public String viewCategories(Model model) {
		model.addAttribute("activePage", "categories");
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		return "categories";
	}
	
	@GetMapping("/categories/add")
	public String showAddCategoryForm(Model model) {
		model.addAttribute("activePage", "categories");
        model.addAttribute("category", new Category());
        return "add-category";
    }
	
	@PostMapping("/categories/add")
    public String addCategory(@Valid @ModelAttribute Category category, BindingResult result) {
		if (result.hasErrors()) {
			return "add-category";
		}
		categoryService.addCategory(category);
		return "redirect:/categories";
	}
	
	@GetMapping("/categories/remove/{categoryId}")
	public String removeCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return "redirect:/categories";
    }
}
