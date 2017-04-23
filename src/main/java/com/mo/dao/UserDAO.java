package com.mo.dao;

import com.mo.businessObject.User;

public interface UserDAO {

	public void saveUser(User user);
	
	public User checkUser(String email);
}
