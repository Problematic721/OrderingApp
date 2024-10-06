package com.example.orderingapp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.orderingapp.model.Dish;
import com.example.orderingapp.service.CategoryService;
import com.example.orderingapp.service.DishService;

import jakarta.validation.Valid;

@Controller
public class DashboardController {

	private final DishService dishService;

	public DashboardController(DishService dishService) {
		this.dishService = dishService;
	}

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/dashboard")
	public String viewDashboard(Model model) {
		model.addAttribute("activePage", "dashboard");
		model.addAttribute("categories", categoryService.getAllCategories());
		return "dashboard";
	}

	@GetMapping("/dashboard/add")
	public String showAddDishForm(Model model) {
		model.addAttribute("activePage", "dashboard");
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("dish", new Dish());
		return "add-dish";
	}

	@PostMapping("dashboard/add")
	public String addDish(@Valid @ModelAttribute Dish dish, @RequestParam MultipartFile image, BindingResult result)
			throws IOException {
		if (result.hasErrors()) {
			return "add-dish";
		}
		if (!image.isEmpty()) {
			String filename = dishService.saveImage(image);
			dish.setImageUrl(filename);
		}
		dishService.createDish(dish);
		return "redirect:/dashboard";
	}

	@GetMapping("/dashboard/details/{dishId}")
	public String viewDishDetails(@PathVariable Long dishId, Model model) {
		model.addAttribute("activePage", "dashboard");
		Dish dish = dishService.getDish(dishId);
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("dish", dish);
		return "dish-details";
	}

	@PostMapping("/dashboard/details/update/{dishId}")
	public String updateDish(@PathVariable Long dishId, @Valid @ModelAttribute Dish updatedDish, BindingResult result,
			@RequestParam("deleteImage") boolean deleteImageFlag, @RequestParam("image") MultipartFile imageFile,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "/dashboard/details/" + dishId;
		}
		Dish existingDish = dishService.getDish(dishId);
		try {

			if (deleteImageFlag && existingDish.getImageUrl() != null) {
				dishService.deleteImage(existingDish.getImageUrl());
				existingDish.setImageUrl(null);
			}
			if (!imageFile.isEmpty()) {
				if (updatedDish.getImageUrl() != null) {
					dishService.deleteImage(updatedDish.getImageUrl());
				}
				String newImageUrl = dishService.saveImage(imageFile);
				existingDish.setImageUrl(newImageUrl);
			}
			existingDish.setName(updatedDish.getName());
			existingDish.setDescription(updatedDish.getDescription());
			existingDish.setPrice(updatedDish.getPrice());
			existingDish.setCategory(updatedDish.getCategory());
			redirectAttributes.addFlashAttribute("successMessage", "Dish updated successfully.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to update dish.");
		}
		dishService.updateDish(dishId, existingDish);
		return "redirect:/dashboard/details/" + dishId;
	}

	@GetMapping("/dashboard/remove/{dishId}")
	public String removeDish(@PathVariable Long dishId) {
		dishService.deleteDish(dishId);
		return "redirect:/dashboard";
	}

}
