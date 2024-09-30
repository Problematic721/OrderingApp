package com.example.orderingapp.service;

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
        CustomerTable newTable = new CustomerTable(tableName);
        return customerTableRepository.save(newTable);
    }
}
