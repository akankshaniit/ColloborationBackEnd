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

import com.niit.collaboration.dao.ChatForumCommentDAO;
import com.niit.collaboration.model.ChatForumComment;
import com.niit.collaboration.model.Event;

@Repository("chatForumCommentDAO")
@Transactional
public class ChatForumCommentDAOImpl implements ChatForumCommentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	 private static Logger log = LoggerFactory.getLogger(ChatForumComment.class);
		
	
	 @Override
		public ChatForumComment get(String id) {
			ChatForumComment chatForumComment=(ChatForumComment) sessionFactory.openSession().load("ChatForumComment.class", id);
			return chatForumComment;
		}

	 
	 
	@Override
	public List<ChatForumComment> list() {
		return sessionFactory.openSession().createQuery("from ChatForumComment").list();
	}

	@Override
	public boolean save(ChatForumComment chatForumComment) {
		try {
			Session session=sessionFactory.openSession();
			session.save(chatForumComment);
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
	public boolean update(ChatForumComment chatForumComment) {
		try {
			Session session=sessionFactory.openSession();
			session.update(chatForumComment);
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
