package com.coderslab.model;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class Product {

	private int pid;
	@NotEmpty(message = "Please Insert Product Name")
	private String pname;
	@Min(value = 1, message = "Price Must Be positive number")
	private double price;
	@Min(value = 1, message = "Quantity Must Be positive number")
	private int qty;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", price=" + price + ", qty=" + qty + "]";
	}

}
