package com.niit.collaboration.dao;



import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.Comments;



public interface CommentDAO {

	public Comments get(String id) ;
		

	public List<Comments> getComments(String blogId) ;


	public void add(Comments comment) ;


	public void remove(Comments comment);

}