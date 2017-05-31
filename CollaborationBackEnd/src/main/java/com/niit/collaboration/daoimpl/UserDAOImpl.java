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
		User user= (User)sessionFactory.openSession().get(User.class, id);
		return user;
	}

	public List<User> list() {
		return sessionFactory.openSession().createQuery("from User").list();
	}

	public User isValidate(String email, String password) {
		log.debug("->->Starting of the method isValidUserDetails");
		String hql = "from User where email= '" + email + "' and " + " password ='" + password+"'";
		
		log.debug("Query is : "+hql);
		
		Query query = sessionFactory.openSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();
		
		if (list != null && !list.isEmpty()) {
			User u=(User) list.get(0);
			log.debug("Got:"+u.getName());
			return u;
		}
		
		return null;
	
		
	}

	public boolean save(User user) {
		try
		{
			
			Session session =sessionFactory.openSession();
			int newidno =session.createQuery("from User").list().size() +1;
			String id ="U"+newidno;
			user.setId(id);
			
			
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

	@Override
	public List<User> notMyFriendList(String loggedInUserID) {
		// TODO Auto-generated method stub
		return sessionFactory.openSession().createQuery("from User").list();
	}

	@Override
	public User getEmail(String email) {
		
		return (User) sessionFactory.openSession().createQuery("from User where email='"+email+"'").uniqueResult();
	}

	@Override
	public void setOnline(String userID) {
		log.debug("Starting of the metnod setOnline");
		String hql =" UPDATE User	SET isOnline = 'Y' where id='" +  userID + "'" ;
		  log.debug("hql: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		log.debug("Ending of the metnod setOnline");
		
	}

	@Override
	public void setOffLine(String userID) {
		log.debug("Starting of the metnod setOffLine");
		String hql =" UPDATE User	SET isOnline = 'N' where id='" +  userID + "'" ;
		  log.debug("hql: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		log.debug("Ending of the metnod setOffLine");
		
	}

	

}
