package com.example.orderingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.orderingapp.model.CustomerTable;
import com.example.orderingapp.service.CategoryService;
import com.example.orderingapp.service.CustomerTableService;
import com.example.orderingapp.service.DishService;

@Controller
public class MenuController {
	
	private final CustomerTableService customerTableService;
	private final DishService dishService;
	private final CategoryService categoryService;
	
    public MenuController(CustomerTableService customerTableService, DishService dishService, CategoryService categoryService) {
        this.customerTableService = customerTableService;
        this.dishService = dishService;
        this.categoryService = categoryService;
    }
	
	@GetMapping("/")
	public String showHome() {
		return "redirect:/menu";
	}
	
	@GetMapping("/menu")
	public String showMenu(@RequestParam(required = false) String tableCode, Model model) {
	    model.addAttribute("dishes", dishService.getAllDishes());
	    model.addAttribute("categories", categoryService.getAllCategories());
		if (tableCode != null && !tableCode.isEmpty()) {
	        CustomerTable customerTable = customerTableService.getTableByTableCode(tableCode);
	        if (customerTable != null) {
	            model.addAttribute("tableCode", tableCode);
	            return "menu";
	        } else {
	            model.addAttribute("error", "Invalid table code. Please scan again");
	        }
	    }
	    return "generic-menu";
	}
	
	@PostMapping("/menu/{tableId}/cart") 
	public String addToCart (@PathVariable Long tableId)  {
        return "redirect:/menu/" + tableId;
	}
}
	