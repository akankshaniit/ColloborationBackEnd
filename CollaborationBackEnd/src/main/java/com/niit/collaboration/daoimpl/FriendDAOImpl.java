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

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.model.Friend;

@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Override
	public Friend get(String id) {
Session session=	(Session) sessionFactory.openSession().load("Friend.class", id);
		return null;
	}

	@Override
	public List<Friend> list() {
		
		return sessionFactory.openSession().createQuery("from Friend").list();
	}

	@Override
	public boolean save(Friend friend) {
		try {
			Session session=sessionFactory.openSession();
			session.save(friend);
			session.flush();
			session.close();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	}

	@Override
	public boolean update(Friend friend) {
		try {
			Session session=sessionFactory.openSession();
			session.update(friend);
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
	public boolean delete(String id) {
		log.debug("Starting of the method delete of Friend");
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
		log.debug("Ending of the method delete of Friend");
		return true;
	}

}