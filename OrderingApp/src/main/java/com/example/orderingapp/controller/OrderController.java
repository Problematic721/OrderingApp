package com.example.orderingapp.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.orderingapp.dto.OrderDTO;
import com.example.orderingapp.model.CustomerOrder;
import com.example.orderingapp.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {
	
	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
    }
	
	
	@PostMapping("/place")
	public ResponseEntity<CustomerOrder> placeOrder(@RequestBody OrderDTO orderDTO) {
		CustomerOrder order = orderService.placeOrder(orderDTO);
        return ResponseEntity.ok(order);
    }
}