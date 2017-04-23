package com.mo.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mo.businessObject.User;
import com.mo.dao.UserDAO;
import com.mo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	
	@Override
	@Transactional
	public void saveUser(User user){
		userDAO.saveUser(user);
	}
	
	@Override
	@Transactional
	public User checkUser(String email){
		return userDAO.checkUser(email);
	}
}
