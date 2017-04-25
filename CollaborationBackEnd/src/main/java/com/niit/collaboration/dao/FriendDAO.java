package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Friend;


public interface FriendDAO {

public Friend get(String id);
	
	public List<Friend> list();
	
	
	
	public boolean save(Friend friend);
	public boolean update(Friend friend);
	public boolean delete(String id);
}
