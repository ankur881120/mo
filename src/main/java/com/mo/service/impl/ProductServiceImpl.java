package com.mo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mo.businessObject.Bill;
import com.mo.businessObject.BillUnit;
import com.mo.businessObject.Product;
import com.mo.dao.ProductDAO;
import com.mo.service.ProductService;
import com.mo.util.ApplicationConstant;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDAO productDAO;
	
	@Override
	@Transactional
	public void saveProduct(Product product){
		productDAO.saveProduct(product);
	}
	
	@Override
	@Transactional
	public Product getproduct(String productId){
		return productDAO.getproduct(productId);
	}
	
	@Override
	@Transactional
	public List<Product> getAllProducts(String category){
		return productDAO.getAllProducts(category);
	}
	
	@Override
	@Transactional
	public Bill generateBill(List<String> productIds, List<Integer> quantityList){
		Bill bill=new Bill();
		Product product=null;
		BillUnit billUnit=null;
		int counter=0;
		double price=0;
		double tax=0;
		for (String productId : productIds) {
			product=productDAO.getproduct(productId);
			if(product!=null){
				if(bill==null){
					bill = new Bill();
					bill.setTotalPrice(0);
				}
				billUnit= new BillUnit();
				billUnit.setProductId(product.getName());
				billUnit.setQuantity(quantityList.get(counter)!=null?quantityList.get(counter):1);
				price=(product.getUnitPrice()*billUnit.getQuantity());
				if(product.getCategory().equals(ApplicationConstant.PRODUCT_CATEGORY_A)){
					tax=(ApplicationConstant.PRODUCT_CATEGORY_A_TAX*price)/100;
				}else if(product.getCategory().equals(ApplicationConstant.PRODUCT_CATEGORY_B)){
					tax=(ApplicationConstant.PRODUCT_CATEGORY_B_TAX*price)/100;
				}else if(product.getCategory().equals(ApplicationConstant.PRODUCT_CATEGORY_C)){
					tax=(ApplicationConstant.PRODUCT_CATEGORY_C_TAX*price)/100;
				}
				price=price+tax;
				bill.setTotalPrice(bill.getTotalPrice()+price);
				bill.getBillingUnits().add(billUnit);
			}
			counter++;
		}
		return bill;
	}
}
