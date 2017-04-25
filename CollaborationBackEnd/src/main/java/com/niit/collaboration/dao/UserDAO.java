package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.User;

public interface UserDAO {

	public User get(String id);
	
	public List<User> list();
	
	
	//if we not adding spring security
	public boolean isValidate(String id , String password);
	public boolean save(User user);
	public boolean update(User user);
	public boolean delete(String id);
}
