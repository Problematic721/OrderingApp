package com.example.orderingapp.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.orderingapp.model.CustomerTable;
import com.example.orderingapp.service.CustomerTableService;
import com.example.orderingapp.utils.QRCodeGenerator;
import com.google.zxing.WriterException;

@Controller
@RequestMapping("/tables")
public class CustomerTableController {
	private final CustomerTableService customerTableService;
	
	public CustomerTableController(CustomerTableService customerTableService) {
        this.customerTableService = customerTableService;
    }
	
	@GetMapping("/")
	public String viewTables(Model model) {
		model.addAttribute("activePage","tables");
        model.addAttribute("tables", customerTableService.getAllTables());
        model.addAttribute("newTable", new CustomerTable());
		return "tables";
    }
	
	@PostMapping("/add")
	public String addTable(@RequestParam String tableName) {
		customerTableService.createTable(tableName);
		return "redirect:/tables";
	}
	
	@PostMapping("/remove/{tableId}")
	public String removeTable(@PathVariable Long tableId) {
		customerTableService.removeTable(tableId);
		return "redirect:/tables";
	}
	
	@GetMapping("/edit/{tableId}")
    public String viewTableDetails(@PathVariable Long tableId, Model model) {
		model.addAttribute("activePage","tables");
        model.addAttribute("table", customerTableService.getTable(tableId));
        return "table-details";
    }
	
	@PostMapping("/edit/{tableId}")
	public String editTable(@PathVariable Long tableId, @RequestParam String tableName, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/tables/" + tableId;
		}
		customerTableService.updateTable(tableId, tableName);
		return "tables";
	}
	
	@GetMapping("/generateQr/{tableCode}")
    public ResponseEntity<byte[]> generateQrCode(@PathVariable String tableCode) throws IOException, WriterException {
    	String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    	String url = baseUrl + "/menu?tableCode=" + tableCode;
    	System.out.println("QR URL: " + url);
        byte[] qrImage = QRCodeGenerator.getQRCodeImage(url, 200, 200);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"qr-code.png\"")
                .contentType(MediaType.IMAGE_PNG)
                .body(qrImage);
    }
    
    @GetMapping("/generateQr/{tableCode}/download")
    public ResponseEntity<byte[]> downloadQrCode(@PathVariable String tableCode) throws IOException, WriterException {
        String url = "https://your-domain.com/menu?tableCode=" + tableCode;
        byte[] qrImage = QRCodeGenerator.getQRCodeImage(url, 200, 200);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"table_" + tableCode + "_qr.png\"")
                .contentType(MediaType.IMAGE_PNG)
                .body(qrImage);
    }
}
