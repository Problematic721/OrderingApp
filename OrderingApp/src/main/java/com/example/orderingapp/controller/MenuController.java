package com.example.orderingapp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.orderingapp.model.Dish;
import com.example.orderingapp.service.DishService;

@Controller
public class MenuController {
	
	@Autowired
	private DishService dishService;
	
	@GetMapping("/")
	public String viewHome() {
		return "login";
	}
	
	@GetMapping("/menu/{tableId}")
	public String viewMenu (@PathVariable Long tableId) {
		return "menu";
	}
	
	@GetMapping("/menu")
	public String viewPlainMenu () {
		return "menu";
	}
	
	@PostMapping("/menu/{tableId}/cart") 
	public String addToCart (@PathVariable Long tableId)  {
        return "redirect:/menu/" + tableId;
	}
	
	@GetMapping("/dashboard")
	public String viewDashboard (Model model) {
		 List<Dish> dishes = dishService.getAllDishes();
		 model.addAttribute("dishes", dishes);
        return "dashboard";
    }
	
	@GetMapping("/add-dish")
    public String showAddDishForm(Model model) {
        model.addAttribute("dish", new Dish());
        return "add-dish";
    }
	
	@PostMapping("/add-dish")
	public String addDish (@ModelAttribute Dish dish,@RequestParam MultipartFile image) throws IOException {
		if (!image.isEmpty()) {
			String imageDirectory = System.getProperty("user.dir") + "/uploads";
			String uniqueFilename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
			Path imagePath = Paths.get(imageDirectory, uniqueFilename);
			Files.createDirectories(imagePath.getParent());
			image.transferTo(imagePath.toFile());
			dish.setImageUrl("uploads/" + uniqueFilename);
		}
		dishService.saveDish(dish);
		return "redirect:/add-dish";
	}
}
	