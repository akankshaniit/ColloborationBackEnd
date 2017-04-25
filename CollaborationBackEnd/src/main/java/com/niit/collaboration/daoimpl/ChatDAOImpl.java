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

import com.niit.collaboration.dao.ChatDAO;
import com.niit.collaboration.model.Chat;

@Repository("chatDAO")
@Transactional
public class ChatDAOImpl implements ChatDAO {

private static Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Chat get(String id) {
		Chat chat = (Chat) sessionFactory.openSession().load("Chat.class", id);
		return chat;
	}

	@Override
	public List<Chat> list() {
		
		return sessionFactory.openSession().createQuery("from Chat").list();
	}

	@Override
	public boolean save(Chat chat) {
		try {
			Session session=sessionFactory.openSession();
			session.save(chat);
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
	public boolean update(Chat chat) {
		try {
			Session session=sessionFactory.openSession();
			session.update(chat);
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
