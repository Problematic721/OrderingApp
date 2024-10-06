package com.example.orderingapp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.orderingapp.model.CustomerTable;
import com.example.orderingapp.repository.CustomerTableRepository;

@Service
public class CustomerTableService {
	private final CustomerTableRepository customerTableRepository;

	public CustomerTableService(CustomerTableRepository customerTableRepository) {
		this.customerTableRepository = customerTableRepository;
	}
	
	public CustomerTable createTable(String tableName) {
		String tableCode = UUID.randomUUID().toString();
        CustomerTable newTable = new CustomerTable(tableName, tableCode);
        return customerTableRepository.save(newTable);
    }

	public void removeTable(Long tableId) {
		customerTableRepository.deleteById(tableId);
	}

	public List<CustomerTable>getAllTables() {
		return customerTableRepository.findAll();
	}

	public CustomerTable getTable(Long tableId) {
		return customerTableRepository.findById(tableId)
			.orElseThrow(() -> new IllegalArgumentException("Table not found"));
	}

	public void updateTable(Long tableId, String tableName) {
		CustomerTable existingTable = this.getTable(tableId);
		existingTable.setTableName(tableName);
		customerTableRepository.save(existingTable);
	}
}
