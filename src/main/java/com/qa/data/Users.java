package com.qa.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Users {
	
	String name;
	String type;
	String upc;
	double price;
	String description;
	String model;
	//int id;
	
	public Users(String name,String type,String upc,double price,String description,String model)
	{
		this.name = name;
		this.type = type;
		this.upc = upc;
		this.price = price;
		this.description = description;
		this.model = model;
	}
/*	@JsonIgnore
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}*/
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
}
