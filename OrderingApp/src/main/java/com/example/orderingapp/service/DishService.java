package com.example.orderingapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.orderingapp.model.Dish;
import com.example.orderingapp.repository.DishRepository;

@Service
public class DishService {
	
	private final DishRepository dishRepository;
	
    public DishService(DishRepository menuItemRepository) {
        this.dishRepository = menuItemRepository;
    }

	public List<Dish> getAllDishes() {
		return dishRepository.findAll();
	}

	public void saveDish(Dish dish) {
		dishRepository.save(dish);
	}

	public Dish getDishById(Long dishId) {
		return dishRepository.findById(dishId)
				.orElseThrow(() -> new IllegalArgumentException("Dish ID not found!"));
	}

	public void deleteById(Long dishId) {
		dishRepository.deleteById(dishId);
		
	}

}
