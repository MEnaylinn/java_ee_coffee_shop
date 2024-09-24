package com.coffee.model;

public class User {
	private Long id;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String role;
	private String password;
	private Boolean status;
	public User() {
		super();
	}
	public User(String name, String address, String phone, String email, String role, String password, Boolean status) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.role = role;
		this.password = password;
		this.status = status;
	}
	public User(Long id, String name, String address, String phone, String email, String role, String password,
			Boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.role = role;
		this.password = password;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email
				+ ", role=" + role + ", password=" + password + ", status=" + status + "]";
	}
	
	
	
	

}
