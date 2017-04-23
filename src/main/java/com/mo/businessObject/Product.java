package com.mo.businessObject;

import java.io.Serializable;

public class Product  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3973477352876102478L;
	private String id;
	private String category;
	private String name;
	private String description;
	private double unitPrice;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
}
