package com.example.orderingapp.dto;

import java.util.List;

public class OrderDTO {
	private String tableCode;
    private List<OrderItemDTO> orderItems;
    
	public String getTableCode() {
		return tableCode;
	}
	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}
	public List<OrderItemDTO> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}
}
