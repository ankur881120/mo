package com.mo.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mo.businessObject.Product;
import com.mo.dao.ProductDAO;

@Repository
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	public ProductDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	} 
	
	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void saveProduct(Product product){
		currentSession().saveOrUpdate(product);
	}
	
	@Override
	public Product getproduct(String productId){
		return (Product)currentSession().get(Product.class, productId);
	}
	
	@Override
	public List<Product> getAllProducts(String category){
		Criteria criteria=currentSession().createCriteria(Product.class);
		if(category!=null){
			criteria.add(Restrictions.eq("category", category));
		}
		return (List<Product>)criteria.list();
	}
}
