package com.mo.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mo.businessObject.User;
import com.mo.dao.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	public UserDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	public Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void saveUser(User user){
		currentSession().saveOrUpdate(user);
	}
	
	@Override
	public User checkUser(String email){
		Criteria criteria = currentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		return (User) criteria.uniqueResult();
	}
}
