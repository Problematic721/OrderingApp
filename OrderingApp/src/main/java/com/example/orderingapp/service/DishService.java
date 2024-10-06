package com.example.orderingapp.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.orderingapp.model.Category;
import com.example.orderingapp.model.Dish;
import com.example.orderingapp.repository.CategoryRepository;
import com.example.orderingapp.repository.DishRepository;

@Service
public class DishService {
	
	private final CategoryRepository categoryRepository;
	private final DishRepository dishRepository;
	private final String imageDirectory = "/uploads/";
	
    public DishService(DishRepository menuItemRepository, CategoryRepository categoryRepository) {
        this.dishRepository = menuItemRepository;
        this.categoryRepository = categoryRepository;
    }

	public List<Dish> getAllDishes() {
		return dishRepository.findAll();
	}

	public void createDish(Dish dish) {
		dishRepository.save(dish);
	}

	public Dish getDish(Long dishId) {
		return dishRepository.findById(dishId)
				.orElseThrow(() -> new IllegalArgumentException("Dish not found!"));
	}

	public void deleteDish(Long dishId) {
		deleteImage(getDish(dishId).getImageUrl());
		dishRepository.delete(getDish(dishId));
	}
	
	public void updateDish(Long dishId, Dish updatedDish) {
		Dish existingDish = getDish(dishId);
		existingDish.setName(updatedDish.getName());
		existingDish.setDescription(updatedDish.getDescription());
		existingDish.setPrice(updatedDish.getPrice());
		 if (updatedDish.getImageUrl() != null && !updatedDish.getImageUrl().isEmpty()) {
		        existingDish.setImageUrl(updatedDish.getImageUrl());
		    }
		if (updatedDish.getCategory() != null) {
            Category category = categoryRepository.findById(updatedDish.getCategory().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
            existingDish.setCategory(category);
        }

		dishRepository.save(existingDish);
	}
	
	public String saveImage(MultipartFile image) throws IOException {
		String imageDirectory = System.getProperty("user.dir") + "/uploads";
		String uniqueFilename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
		Path imagePath = Paths.get(imageDirectory, uniqueFilename);
		Files.createDirectories(imagePath.getParent());
		image.transferTo(imagePath.toFile());
		return uniqueFilename;
	}
	
	public void deleteImage(String imageUrl) {
		 if (imageUrl != null && !imageUrl.isEmpty()) {
	            try {
	                Path imagePath = Paths.get(imageDirectory + imageUrl);
	                Files.deleteIfExists(imagePath); 
	            } catch (IOException e) {
	                System.err.println("Failed to delete image: " + imageUrl);
	            }
	        }
	}
}
