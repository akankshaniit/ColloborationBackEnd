package com.niit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.JobApplicationDAO;
import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.JobApplication;
import com.niit.collaboration.model.User;

public class JobApplicationTestCase {
	
	@Autowired
	static AnnotationConfigApplicationContext  context;
	
	@Autowired
	private static JobApplication jobApplication;
	
	@Autowired
	private static JobApplicationDAO jobApplicationDAO;
	
	@BeforeClass
	public static void init()
	{
	context	=new  AnnotationConfigApplicationContext ();
		context.scan("com.niit");
		context.refresh();
		
		jobApplication= (JobApplication) context.getBean("jobApplication");
		jobApplicationDAO= (JobApplicationDAO) context.getBean("jobApplicationDAO");
		
		
	}
	
	@Test
	public void createJobAppTestCase()
	{
		jobApplication.setId("002");
		jobApplication.setJobid("J006");
		jobApplication.setUserid("U003");
		jobApplication.setStatus('W');
		jobApplication.setRemark("Waiting");
		jobApplication.setDateApplied(null);
		
		boolean flag= jobApplicationDAO.save(jobApplication);
		assertEquals("createJobAppTestCase",true,flag);
	}
	
	@Test
	public void updateJobAppTestCase()
	{
		jobApplication.setId("001");
		jobApplication.setJobid("J004");
		jobApplication.setUserid("U005");
		jobApplication.setStatus('R');
		jobApplication.setRemark("Rejected");
		jobApplication.setDateApplied(null);
		
		boolean flag= jobApplicationDAO.update(jobApplication);
		assertEquals("updateJobAppTestCase",true,flag);
	}
	
	@Test
	public void getJobAppTestCase()
	{
		jobApplication= jobApplicationDAO.get("j003");
		assertEquals("getJobAppTestCase", null,jobApplication);
	}
	
	@Test
	public void getAllJobAppTestCase()
	{
		int size=jobApplicationDAO.list().size();
		assertEquals("getAllJobAppTestCase",2,size);
	}
	
	
}
