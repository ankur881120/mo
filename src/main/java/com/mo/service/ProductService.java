package com.mo.service;

import java.util.List;

import com.mo.businessObject.Bill;
import com.mo.businessObject.Product;

public interface ProductService {

	public void saveProduct(Product product);
	
	public Product getproduct(String productId);
	
	public List<Product> getAllProducts(String category);
	
	public Bill generateBill(List<String> productIds, List<Integer> quantityList);
}
