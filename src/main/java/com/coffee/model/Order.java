package com.coffee.model;

import java.time.LocalDate;

public class Order {
	private Long id;
	private Long orderid;
	private Long userid;
	private Long itemid;
	private Integer quantity;
	private Double price;
	private Boolean status;
	private LocalDate date;
	
	public Order() {
		super();
	}

	public Order(Long orderid, Long userid, Long itemid, Integer quantity, Double price, Boolean status,
			LocalDate date) {
		super();
		this.orderid = orderid;
		this.userid = userid;
		this.itemid = itemid;
		this.quantity = quantity;
		this.price = price;
		this.status = status;
		this.date = date;
	}

	public Order(Long id, Long orderid, Long userid, Long itemid, Integer quantity, Double price, Boolean status,
			LocalDate date) {
		super();
		this.id = id;
		this.orderid = orderid;
		this.userid = userid;
		this.itemid = itemid;
		this.quantity = quantity;
		this.price = price;
		this.status = status;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getItemid() {
		return itemid;
	}

	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderid=" + orderid + ", userid=" + userid + ", itemid=" + itemid + ", quantity="
				+ quantity + ", price=" + price + ", status=" + status + ", date=" + date + "]";
	}
	
	
	

}