package com.example.orderingapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.orderingapp.dto.OrderDTO;
import com.example.orderingapp.dto.OrderItemDTO;
import com.example.orderingapp.model.CustomerTable;
import com.example.orderingapp.model.Dish;
import com.example.orderingapp.model.CustomerOrder;
import com.example.orderingapp.model.OrderItem;
import com.example.orderingapp.model.Status;
import com.example.orderingapp.repository.OrderRepository;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final CustomerTableService customerTableService;
	private final DishService dishService;
	
	public OrderService(OrderRepository orderRepository, CustomerTableService customerTableService, DishService dishService) {
        this.orderRepository = orderRepository;
        this.customerTableService = customerTableService;
        this.dishService = dishService;
    }
	
	public void getOrder(long orderId) {
		orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
	}
	
	public List<CustomerOrder> getAllOrder() {
		return orderRepository.findAll();
	}

	public CustomerOrder placeOrder(OrderDTO orderDTO) {
		CustomerTable customerTable = customerTableService.getTableByTableCode(orderDTO.getTableCode());
		CustomerOrder order = new CustomerOrder();
		order.setCustomerTable(customerTable);
		order.setOrderTime(LocalDateTime.now());
		order.setStatus(Status.In_Progress);
		
		List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            Dish dish = dishService.getDish(itemDTO.getDishId());

            OrderItem orderItem = new OrderItem();
            orderItem.setDish(dish);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        
        order.setOrderItems(orderItems);
        System.out.println("Order: " + order);
        return orderRepository.save(order);
	}
}
