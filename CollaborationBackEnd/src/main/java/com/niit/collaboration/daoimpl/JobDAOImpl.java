package com.niit.collaboration.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.JobDAO;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;


@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger log = LoggerFactory.getLogger(JobDAOImpl.class);
	
	
	public Job get(String id) {
	Job job=	(Job) sessionFactory.openSession().get(Job.class, id);
		return job;
	}

	public List<Job> list() {
		
		return sessionFactory.openSession().createQuery("from Job").list();
	}

	public boolean save(Job job) {
		try {
			Session session=sessionFactory.openSession();
			session.save(job);
			session.flush();
			session.close();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Job job) {
		try {
			Session session=sessionFactory.openSession();
			session.update(job);
			session.flush();
			session.close();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(String id) {
		log.debug("Starting of the method delete of job");
		log.debug("Trying to delete the record : " + id);
		try
		{
		
		sessionFactory.openSession().delete(get(id));
		log.debug("successfully deleted the record :" + id);
		}catch(Exception e)
		{
			log.debug("record does not exist with the id " + id);
			return false;
			
		}
		log.debug("Ending of the method delete of job");
		return true;
	}

	@Override
	public boolean save(JobApplication jobApplication) {
		try {
			Session session=sessionFactory.openSession();
			session.save(jobApplication);
			session.flush();
			session.close();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateJob(JobApplication jobApplication) {
		try {
			Session session=sessionFactory.openSession();
			session.update(jobApplication);
			session.flush();
			session.close();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	public JobApplication getJobApplication(String id) {
		JobApplication jobApplication= (JobApplication) sessionFactory.openSession().load("JobApplication.class", id);
		return jobApplication;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getMyAppliedJobs(String userID) {
		return	(List<Job>) sessionFactory.openSession().createQuery("select * from JobApplication where User_id=?");
	
	}

	@Override
	public JobApplication getJobApplication(String userID, String jobID) {
		
	return	(JobApplication) sessionFactory.openSession().createQuery("select * from JobApplication where user_id = ? and job_id=?");
	}

	@Override
	public List<Job> getAllOpendJobs() {
		
		return sessionFactory.openSession().createQuery("from Job where status='V'").list();
	}

}
