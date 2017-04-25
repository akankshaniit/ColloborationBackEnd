package com.niit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.User;

public class BlogDAOTestCase {

	@Autowired
	static AnnotationConfigApplicationContext  context;
	
	@Autowired
	private static  Blog blog;
	
	@Autowired
	private static BlogDAO blogDAO;
	
	@BeforeClass
	public static void init()
	{
	context	=new  AnnotationConfigApplicationContext ();
		context.scan("com.niit");
		context.refresh();
		
		blog= (Blog) context.getBean("blog");
		blogDAO= (BlogDAO) context.getBean("blogDAO");
		
	}
	
	
	@Test
	public void createBlogTestCase()
	{
		blog.setId("B003");
		blog.setTitle("FriendsChat");
		blog.setUser_id("U006");
		blog.setStatus('A');
		blog.setDescription("stay Connected with Me");
		blog.setReason("Connection Checking In Free time");
		blog.setDateTime(null);
		
		boolean flag=blogDAO.save(blog);
		assertEquals("createBlogTestCase",true,flag);
	}
	
	@Test
	public void updateBlogTestCase()
	{
		blog.setId("B001");
		blog.setTitle("FriendsChat");
		blog.setUser_id("U002");
		blog.setStatus('A');
		blog.setDescription("stay Connected in Free Time");
		blog.setReason("Connection");
		
		boolean flag =blogDAO.update(blog);
		assertEquals("updateBlogTestCase", true,flag);

	}
	
	//@Test
	public void getBlogTestCase()
	{
		blog=blogDAO.get("B005");
		assertEquals("getBlogTestCAse",null,blog);
		
		
	}
	
	//@Test
	public void getAllBlogTestCase()
	{
		int size=blogDAO.list().size();
		assertEquals("getBlogTestCase",3,size);
	}
}
