package com.coffee.model;

import java.time.LocalDate;

public class Voucher {
	private Long id;
	private Long code;
	private Double total;
	private LocalDate date;
	public Voucher() {
		// TODO Auto-generated constructor stub
	}
	
	public Voucher(Long code, Double total, LocalDate date) {
		super();
		this.code = code;
		this.total = total;
		this.date = date;
	}

	public Voucher(Long id, Long code, Double total, LocalDate date) {
		super();
		this.id = id;
		this.code = code;
		this.total = total;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Voucher [id=" + id + ", code=" + code + ", total=" + total + ", date=" + date + "]";
	}
	
	
	
	

}