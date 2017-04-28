package com.niit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.JobDAO;
import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.User;

public class JobDAOTestCase {
	
	@Autowired
	static AnnotationConfigApplicationContext  context;
	
	@Autowired
	private static Job job;
	
	@Autowired
	private static JobDAO jobDAO;
	
	@BeforeClass
	public static void init()
	{
	context	=new  AnnotationConfigApplicationContext ();
		context.scan("com.niit");
		context.refresh();
		
		job= (Job) context.getBean("job");
		jobDAO= (JobDAO) context.getBean("jobDAO");
		
		
	}
	
	@Test
	public void createJobTestCase()
	{
		job.setId("J002");
		job.setTitle("IT Vacancy for Fresher");
		job.setDescription("Apply Here ");
		job.setStatus('V');
		job.setQualification("BTech Engg");
		job.setDate_Time(null);
		
		boolean flag=jobDAO.save(job);
		assertEquals("createJobTestCase",true,flag);
	}
	
	@Test
	public void updateJobTestCase()
	{
		job.setId("J001");
		job.setTitle("IT and Comp Vacancy");
		job.setDescription("Apply Here... ");
		job.setStatus('C');
		job.setQualification("BTech Engg only");
		job.setDate_Time(null);
		
		boolean flag=jobDAO.update(job);
		assertEquals("updateJobTestCase",true,flag);
	}
	
	@Test
	public void getJobTestCase()
	{
		job= jobDAO.get("J003");
		assertEquals("getJobTestCase", null,job);
	}
	
	@Test
	public void getAllJobTestCase()
	{
		int size=jobDAO.list().size();
		assertEquals("getAllJobTestCase",2,size);
	}
}
