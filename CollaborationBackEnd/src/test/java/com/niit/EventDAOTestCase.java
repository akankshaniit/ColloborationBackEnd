package com.niit;

import static org.junit.Assert.assertEquals;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;

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
		event.setId("E002");
		event.setName("Job Fairwell for Placement");
		event.setVenue("Mumbai");
		//event.setImage(null);
		
		event.setDescription("here Description");
		event.setDateTime(null);
		
		boolean flag= eventDAO.save(event);
		assertEquals("createEventTestCase",true,flag);
		
	}
	
	@Test
	public void updateEventTestCase()
	{
		event.setId("E001");
		event.setName("Job Fairwell for placement.");
		event.setVenue("new panvel.");
		//event.setImage(null);
		event.setDescription("description");
		event.setDateTime(null);

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
		assertEquals("getAllEventTestCase",2,size);
	}
		
	
	
}
