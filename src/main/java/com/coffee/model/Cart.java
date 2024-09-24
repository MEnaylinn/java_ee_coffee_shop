package com.coffee.model;

public class Cart extends Item {
	
	private Integer orderqt;
	
	

	public Cart() {}
	

	public Cart(String name, String category, Double price, Boolean status, Integer orderqt) {
		super(name, category, price, status);
		this.orderqt = orderqt;
	}

	public Cart(Long id, String name, String category, Double price, Boolean status, Integer orderqt) {
		super(id, name, category, price, status);
		this.orderqt = orderqt;
	}

	public Integer getOrderqt() {
		return orderqt;
	}

	public void setOrderqt(Integer orderqt) {
		this.orderqt = orderqt;
	}

	@Override
	public String toString() {
		return "Cart [orderqt=" + orderqt + "]";
	}

	

	

	

	
	
}
