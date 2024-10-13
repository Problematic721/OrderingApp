package com.example.orderingapp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class CustomerOrder {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private CustomerTable customerTable;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@JsonManagedReference
    private List<OrderItem> orderItems = new ArrayList<>();
	
	private LocalDateTime orderTime;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CustomerTable getCustomerTable() {
		return customerTable;
	}

	public void setCustomerTable(CustomerTable customerTable) {
		this.customerTable = customerTable;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	public LocalDateTime getOrderTime() {
        return orderTime;
    }
	
	public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
	    return "CustomerOrder{" +
	            "id=" + id +
	            ", customerTable=" + (customerTable != null ? customerTable.getTableCode() : "null") +
	            ", orderTime=" + orderTime +
	            ", status=" + status +
	            ", orderItems=" + orderItems + // Caution: This could lead to infinite recursion if OrderItems have a reference back to CustomerOrder
	            '}';
	}
	
}
