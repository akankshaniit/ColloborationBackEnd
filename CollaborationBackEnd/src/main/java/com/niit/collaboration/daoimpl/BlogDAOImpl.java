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

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.model.Blog;


@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {

	
	private static Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	public Blog get(String id) {
	Blog blog=	(Blog) sessionFactory.openSession().load(Blog.class, id);
		return blog;
	}

	public List<Blog> list() {
		
		return sessionFactory.openSession().createQuery("from Blog").list();
	}

	public boolean save(Blog blog) {
		
		try {
			Session session=sessionFactory.openSession();
			session.save(blog);
			session.flush();
			session.close();
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Blog blog) {
		try {
			Session session=sessionFactory.openSession();
			session.update(blog);
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
		log.debug("Starting of the method delete");
		log.debug("Trying to delte the record : " + id);
		try {
			sessionFactory.openSession().delete(get(id));
			log.debug("successfully delted the record :" + id);
		} catch (HibernateException e) {
			log.debug("record does not exist with the id " + id);
			return false;
			
		}
		log.debug("Ending of the method delete");
		return true;
	}

}
