package com.niit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.BlogDAO;

import com.niit.collaboration.model.Blog;


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
	
	
	//@Test
	public void createBlogTestCase()
	{
		blog.setId("B7");
		blog.setTitle("Silent Group");
		blog.setUser_id("U4");
		blog.setStatus('A');
		blog.setDescription("stay Connected with Me");
		blog.setReason("");
		blog.setDate_time(null);
		
		boolean flag=blogDAO.save(blog);
		assertEquals("createBlogTestCase",true,flag);
	}
	
	//@Test
	public void updateBlogTestCase()
	{
		blog.setId("B4");
		blog.setTitle("FriendsChat");
		blog.setUser_id("U5");
		blog.setStatus('A');
		blog.setDescription("stay Connected in Free Time");
		blog.setReason("");
		
		boolean flag =blogDAO.update(blog);
		assertEquals("updateBlogTestCase", true,flag);

	}
	
	//@Test
	public void getBlogTestCase()
	{
		blog=blogDAO.get("B10");
		assertEquals("getBlogTestCAse",null,blog);
		
		
	}
	
	@Test
	public void getAllBlogTestCase()
	{
		int size=blogDAO.list().size();
		assertEquals("getBlogTestCase",5,size);
	}
}
