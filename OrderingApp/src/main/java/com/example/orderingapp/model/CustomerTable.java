package com.example.orderingapp.model;

import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class CustomerTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@NotBlank (message = "Table must not be empty")
	private String tableName;
	
	@Column(nullable = false, unique = true, updatable = false)
    private String tableCode;

	public CustomerTable(String tableName) {
		setTableName(tableName);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode() {
		this.tableCode = UUID.randomUUID().toString();
	}
	
	
}
