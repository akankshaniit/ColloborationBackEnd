package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.JobApplication;



public interface JobApplicationDAO {

public JobApplication get(String id);
	
	public List<JobApplication> list();
	public List<JobApplication> appliedlist(String userid);
	
	
	public boolean save(JobApplication jobapplication);
	public boolean update(JobApplication jobapplication);
	public boolean delete(String id);
}
