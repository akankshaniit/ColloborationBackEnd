package com.niit;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.User;

import junit.framework.Assert;





public class UserDAOTestCase {

	@Autowired
	static AnnotationConfigApplicationContext  context;
	
	@Autowired
	static User user;
	
	@Autowired
	static UserDAO userDAO;
	
	@BeforeClass
	public static void init()
	{
	context	=new  AnnotationConfigApplicationContext ();
		context.scan("com.niit");
		context.refresh();
		
		user= (User) context.getBean("user");
		userDAO= (UserDAO) context.getBean("userDAO");
		
		
	}
	
	@Test
	public void validateCredentialTestCase()
	{
		boolean flag= userDAO.isValidate("U001", "Akku");
		
		Assert.assertEquals("validateCredentialTestCase", true,flag);
		
	}
	
	@Test
	public void createUserTestCase()
	{
		user.setId("U002");
		user.setName("Niyati");
		user.setPassword("niyu");
		user.setRole("student");
		user.setAddress("Mahad");
		
		user.setMobile("8554016664");
		
		boolean flag= userDAO.save(user);
		assertEquals("createUserTestCase", true, flag);
	}
	
	@Test
	public void updateTestCase()
	{
		user.setId("U001");
		user.setName("Sahar");
		user.setPassword("Akku");
		user.setRole("student");
		user.setAddress("Old-Panvel");
		
		user.setMobile("9922120878");
		
		boolean flag= userDAO.update(user);
		assertEquals("createUserTestCase", true, flag);
	}
	
	@Test
	public void getUserTestCase()
	{
		user= userDAO.get("U009");
		assertEquals("getUserTestCase", null,user);
	}
	
	@Test
	public void getAllUserTestCase()
	{
		int size=userDAO.list().size();
		assertEquals("getAllUserTestCase",2,size);
	}
	
	
}
