package com.example.orderingapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Dish {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Name must not be empty")
	@Column(nullable = false, unique = true)
	private String name;

	private String description;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category_id")
    private Category category;

	private String imageUrl;

	@NotNull(message = "Price must not be empty")
	@Column(nullable = false)
	@Min(value = 0, message = "Price must be greater than or equal to 0")
	private double price;
	
	public Dish() {
	}
	
	public Dish(long id, @NotBlank(message = "Name must not be empty") String name, String description,
			String imageUrl,
			@NotNull(message = "Price must not be empty") @Min(value = 0, message = "Price must be greater than or equal to 0") double price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
