package com.example.orderingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.orderingapp.model.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
	
}
