package com.coffee.model;

public class Item {
	private Long id;
	private String name;
	private String category;
	private Double price;
	private Boolean status;
	
	public Item() {
		
	}
	
	public Item(String name, String category, Double price, Boolean status) {
		super();
		this.name = name;
		this.category = category;
		this.price = price;
		this.status = status;
	}
	public Item(Long id, String name, String category, Double price, Boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", status="
				+ status + "]";
	}
	
	
	

}
