package com.athompson.model;

public class Product {
	private String name;
	private String category;
	private String id;
	private String imagePath;
	private Double price;
	private int amount;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Product(String imagePath, String name, Double price, String category, String id) {
		this.name = name;
		this.category = category;
		this.id = id;
		this.imagePath = imagePath;
		this.price = price;
		this.amount = 1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImagePatj() {
		return imagePath;
	}

	public void setImagePatj(String imagePatj) {
		this.imagePath = imagePatj;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
