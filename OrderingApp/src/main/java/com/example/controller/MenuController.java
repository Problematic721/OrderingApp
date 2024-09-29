package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MenuController {
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
}
	