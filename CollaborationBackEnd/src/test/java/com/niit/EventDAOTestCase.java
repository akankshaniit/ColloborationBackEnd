package com.niit;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.EventDAO;

import com.niit.collaboration.model.Event;

import junit.framework.Assert;

public class EventDAOTestCase {

	@Autowired
	static AnnotationConfigApplicationContext  context;
	
	@Autowired
	private static Event event;
	
	@Autowired
	private static EventDAO eventDAO;
	
	@BeforeClass
	public static void init()
	{
	context	=new  AnnotationConfigApplicationContext ();
		context.scan("com.niit");
		context.refresh();
		
		event= (Event) context.getBean("event");
		eventDAO= (EventDAO) context.getBean("eventDAO");
		
		
	}
	
	@Test
	public void createEventTestCase()
	{
		event.setId("E001");
		event.setName("Job Fairwell");
		event.setVenue("old panvel");
		event.setImage(null);
		
		boolean flag= eventDAO.save(event);
		assertEquals("createEventTestCase",true,flag);
		
	}
	
	@Test
	public void updateEventTestCase()
	{
		event.setId("E001");
		event.setName("Job Fairwell");
		event.setVenue("new panvel");
		event.setImage(null);
		

		boolean flag= eventDAO.update(event);
		assertEquals("createEventTestCase",true,flag);
		
		
	}
	
	
	@Test
	public void getEventTestCase()
	{
		event= eventDAO.get("U003");
		assertEquals("getEventTestCase", null,event);
	}
	
	@Test
	public void getAllUserTestCase()
	{
		int size=eventDAO.list().size();
		assertEquals("getAllEventTestCase",1,size);
	}
		
	
	
}
