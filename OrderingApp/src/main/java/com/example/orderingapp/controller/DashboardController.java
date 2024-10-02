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

import com.example.orderingapp.model.Dish;
import com.example.orderingapp.service.CategoryService;
import com.example.orderingapp.service.DishService;

import jakarta.validation.Valid;

@Controller
public class DashboardController {

	@Autowired
	private DishService dishService;
	
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
			String imageDirectory = System.getProperty("user.dir") + "/uploads";
			String uniqueFilename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
			Path imagePath = Paths.get(imageDirectory, uniqueFilename);
			Files.createDirectories(imagePath.getParent());
			image.transferTo(imagePath.toFile());
			dish.setImageUrl("uploads/" + uniqueFilename);
		}
		dishService.saveDish(dish);
		return "redirect:/dashboard";
	}

	@GetMapping("/dashboard/details/{dishId}")
	public String viewDishDetails(@PathVariable Long dishId, Model model) {
		Dish dish = dishService.getDishById(dishId);
		model.addAttribute("dish", dish);
		return "details";
	}
	
	@GetMapping("/dashboard/remove/{dishId}")
	public String removeDish(@PathVariable Long dishId) {
        dishService.deleteById(dishId);
        return "redirect:/dashboard";
    }
	
}
