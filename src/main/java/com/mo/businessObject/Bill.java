package com.mo.businessObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bill implements Serializable{

	private List<BillUnit> billingUnits = new ArrayList<BillUnit>();
	private double totalPrice;
	
	public List<BillUnit> getBillingUnits() {
		return billingUnits;
	}
	public void setBillingUnits(List<BillUnit> billingUnits) {
		this.billingUnits = billingUnits;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
