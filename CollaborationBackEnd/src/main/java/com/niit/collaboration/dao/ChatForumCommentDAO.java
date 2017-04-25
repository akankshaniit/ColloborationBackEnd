package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.ChatForumComment;


public interface ChatForumCommentDAO {

	public ChatForumComment get(String id);
	public List<ChatForumComment> list();
	
	
	
	public boolean save(ChatForumComment chatForumComment);
	public boolean update(ChatForumComment chatForumComment);
	public boolean delete(String id);
}
