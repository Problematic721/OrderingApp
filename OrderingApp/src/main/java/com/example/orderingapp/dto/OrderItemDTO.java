package com.example.orderingapp.dto;

public class OrderItemDTO {
	private long dishId;
    private int quantity;
    
	public long getDishId() {
		return dishId;
	}
	public void setDishId(long dishId) {
		this.dishId = dishId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
