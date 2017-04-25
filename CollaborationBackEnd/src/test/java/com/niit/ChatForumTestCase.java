package com.niit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.ChatForumCommentDAO;
import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.ChatForumComment;
import com.niit.collaboration.model.User;

public class ChatForumTestCase {

	

	@Autowired
	static AnnotationConfigApplicationContext  context;
	
	@Autowired
	private static ChatForumComment chatForumComment;
	
	@Autowired
	private static ChatForumCommentDAO chatForumCommentDAO;
	
	@BeforeClass
	public static void init()
	{
	context	=new  AnnotationConfigApplicationContext ();
		context.scan("com.niit");
		context.refresh();
		
		chatForumComment= (ChatForumComment) context.getBean("chatForumComment");
		chatForumCommentDAO= (ChatForumCommentDAO) context.getBean("chatForumCommentDAO");
		
		
	}
	
	@Test
   public void createChatForumTestCase()
	{
		chatForumComment.setId("CF001");
		chatForumComment.setUser_id("U001");
		chatForumComment.setMessage("inside ChatForum ");
		chatForumComment.setCommentedDate(null);
		chatForumComment.setForum_id("FO001");
		
		boolean flag =chatForumCommentDAO.save(chatForumComment);
		assertEquals("createChatForumTestCase",true,flag);
	}
	
	@Test
	   public void updateChatForumTestCase()
		{
			chatForumComment.setId("CF001");
			chatForumComment.setUser_id("U002");
			chatForumComment.setMessage("inside ChatForum...updated ");
			chatForumComment.setCommentedDate(null);
			chatForumComment.setForum_id("FO001");
			
			boolean flag =chatForumCommentDAO.update(chatForumComment);
			assertEquals("createChatForumTestCase",true,flag);
		}
	
}
