package com.mo.dao;

import java.util.List;

import com.mo.businessObject.Product;

public interface ProductDAO {

	public void saveProduct(Product product);
	
	public Product getproduct(String productId);
	
	public List<Product> getAllProducts(String category);
}
