package com.niit.collaboration.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.User;



@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	
	
	public User get(String id) {
		User user= (User)sessionFactory.openSession().load(User.class, id);
		return user;
	}

	public List<User> list() {
		return sessionFactory.openSession().createQuery("from User").list();
	}

	public boolean isValidate(String id, String password) {
		Query query=sessionFactory.openSession().createQuery("from User where id=? and password=?");
		query.setString(0,id);
		query.setString(1,password);
		if(query.uniqueResult() ==null)
		{
			return false;
		}
		else
		{
		return true;
		}
		
	}

	public boolean save(User user) {
		try
		{
			Session session =sessionFactory.openSession();
			session.save(user);
			session.flush();
			session.close();
				return true;
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			return false;
		}
		
		
	}

	public boolean update(User user) {
		
		try {
			Session session =sessionFactory.openSession();
			session.update(user);
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
		try
		{
		
		sessionFactory.openSession().delete(get(id));
		log.debug("successfully delted the record :" + id);
		}catch(Exception e)
		{
			log.debug("record does not exist with the id " + id);
			return false;
			
		}
		log.debug("Ending of the method delete");
		return true;
	}

}
