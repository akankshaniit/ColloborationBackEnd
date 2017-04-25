package com.niit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.ChatDAO;
import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.Chat;
import com.niit.collaboration.model.User;

public class ChatDAOTestCase {
	@Autowired
	static AnnotationConfigApplicationContext  context;
	
	@Autowired
	private static Chat chat;
	
	@Autowired
	private static ChatDAO chatDAO;
	
	@BeforeClass
	public static void init()
	{
	context	=new  AnnotationConfigApplicationContext ();
		context.scan("com.niit");
		context.refresh();
		
		chat= (Chat) context.getBean("chat");
		chatDAO= (ChatDAO) context.getBean("chatDAO");
		
		
	}
	
	@Test
	public void createChatTestCase()
	{
		chat.setId("C001");
		chat.setUser_id("U001");
		chat.setMessage("Hiiiii");
		chat.setFriend_id("F001");
		chat.setDateTime(null);
		
		boolean flag=chatDAO.save(chat);
		assertEquals("createChatTestCase",true,flag);
	}
	
	@Test
	public void updateChatTestCase()
	{
		chat.setId("C001");
		chat.setUser_id("U001");
		chat.setMessage("Hiiiii");
		chat.setFriend_id("F002");
		chat.setDateTime(null);
		
		boolean flag=chatDAO.update(chat);
		assertEquals("updateChatTestCase",true,flag);
	}
	
	@Test
	public void getChatTestCase()
	{
		chat= chatDAO.get("U009");
		assertEquals("getChatTestCase", null,chat);
	}
	
	@Test
	public void getAllChatTestCase()
	{
		int size=chatDAO.list().size();
		assertEquals("getAllUserTestCase",1,size);
	}
	
}
