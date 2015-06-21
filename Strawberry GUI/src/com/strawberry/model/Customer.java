package com.strawberry.model;

public class Customer {

	private int customerId;
	private String customerName;
	private String customerMail;
	private String customerPhone;
	private String customerAddress;
	
	public Customer(int customerId, String customerName, String customerMail, String customerPhone, String customerAddress){
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerMail = customerMail;
		this.customerPhone = customerPhone;
		this.customerAddress = customerAddress;
	}
	
	public Customer(String customerName, String customerMail, String customerPhone, String customerAddress){
		this.customerName = customerName;
		this.customerMail = customerMail;
		this.customerPhone = customerPhone;
		this.customerAddress = customerAddress;
	}
	
	public Customer(){}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMail() {
		return customerMail;
	}

	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
}
