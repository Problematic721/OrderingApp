package com.example.orderingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.orderingapp.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository <Category, Long> {
	
}
