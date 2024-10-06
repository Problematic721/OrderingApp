package com.example.orderingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.orderingapp.model.CustomerTable;
import com.example.orderingapp.service.CustomerTableService;

@Controller
public class CustomerTableController {
	private final CustomerTableService customerTableService;
	
	public CustomerTableController(CustomerTableService customerTableService) {
        this.customerTableService = customerTableService;
    }
	
	@GetMapping("/tables")
	public String viewTables(Model model) {
		model.addAttribute("activePage","tables");
        model.addAttribute("tables", customerTableService.getAllTables());
        model.addAttribute("newTable", new CustomerTable());
		return "tables";
    }
	
	@PostMapping("/tables/add")
	public String addTable(@RequestParam String tableName) {
		customerTableService.createTable(tableName);
		return "redirect:/tables";
	}
	
	@PostMapping("/tables/remove/{tableId}")
	public String removeTable(@PathVariable Long tableId) {
		customerTableService.removeTable(tableId);
		return "redirect:/tables";
	}
	
	@GetMapping("/tables/edit/{tableId}")
    public String viewTableDetails(@PathVariable Long tableId, Model model) {
		model.addAttribute("activePage","tables");
        model.addAttribute("table", customerTableService.getTable(tableId));
        return "table-details";
    }
	
	@PostMapping("/tables/edit/{tableId}")
	public String editTable(@PathVariable Long tableId, @RequestParam String tableName, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/tables/" + tableId;
		}
		customerTableService.updateTable(tableId, tableName);
		return "tables";
	}
}
