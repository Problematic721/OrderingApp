package com.example.orderingapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Dish {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Name must not be empty")
	@Column(nullable = false)
	private String name;

	private String description;

	private String image;

	@NotNull(message = "Price must not be empty")
	@Column(nullable = false)
	@Min(value = 0, message = "Price must be greater than or equal to 0")
	private double price;
	
	public Dish(long id, @NotBlank(message = "Name must not be empty") String name, String description,
			String image,
			@NotNull(message = "Price must not be empty") @Min(value = 0, message = "Price must be greater than or equal to 0") double price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
