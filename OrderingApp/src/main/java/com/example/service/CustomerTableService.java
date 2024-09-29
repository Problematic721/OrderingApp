package com.example.service;

import org.springframework.stereotype.Service;

import com.example.model.CustomerTable;
import com.example.repository.CustomerTableRepository;

@Service
public class CustomerTableService {
	private final CustomerTableRepository customerTableRepository;

	public CustomerTableService(CustomerTableRepository customerTableRepository) {
		this.customerTableRepository = customerTableRepository;
	}
	
	public CustomerTable createTable(String tableName) {
        CustomerTable newTable = new CustomerTable(tableName);
        return customerTableRepository.save(newTable);
    }
}
