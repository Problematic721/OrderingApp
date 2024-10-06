package com.example.orderingapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class CustomerTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Table must not be empty")
	private String tableName;

	@Column(nullable = false, unique = true, updatable = false)
	private String tableCode;

	public CustomerTable(String tableName, String tableCode) {
		setTableName(tableName);
		setTableCode(tableCode);
	}

	public CustomerTable() {
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

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

}
