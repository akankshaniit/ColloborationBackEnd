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

import com.niit.collaboration.dao.EventDAO;
import com.niit.collaboration.model.Event;



@Repository("eventDAO")
@Transactional

public class EventDAOImpl implements EventDAO {


	 private static Logger log = LoggerFactory.getLogger(Event.class);
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Event get(String id) {
		Event event=(Event) sessionFactory.openSession().load("Event.class", id);
		return event;
	}

	public List<Event> list() {
		
		return sessionFactory.openSession().createQuery("from Event").list();
	}

	public boolean save(Event event) {
		try {
			Session session=sessionFactory.openSession();
			session.save(event);
			session.flush();
			session.close();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Event event) {
		try {
			Session session=sessionFactory.openSession();
			session.update(event);
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


