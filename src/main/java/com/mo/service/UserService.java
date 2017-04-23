package com.mo.service;

import com.mo.businessObject.User;

public interface UserService {

	public void saveUser(User user);
	
	public User checkUser(String email);
}
