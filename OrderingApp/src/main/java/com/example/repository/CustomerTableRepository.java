package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.CustomerTable;

@Repository
public interface CustomerTableRepository extends JpaRepository <CustomerTable, Long>{
	CustomerTable findByTableName(String tableName);
}
