package com.example.orderingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
}
	