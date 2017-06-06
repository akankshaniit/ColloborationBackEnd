package com.niit.collaboration.daoimpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.CommentDAO;
import com.niit.collaboration.model.Comments;

@Repository("commentDAO")
@Transactional
public class CommentDAOImpl implements CommentDAO {
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory ){
		this.sessionFactory = sessionFactory;
	}

	

	public Comments get(String id) {
		return (Comments) this.sessionFactory.getCurrentSession().get(Comments.class, id);
	}


	public List<Comments> getComments(String blogId) {
		return this.sessionFactory.getCurrentSession().createQuery("from Comments where blog_id=? order by commentedAt desc").setParameter(0, blogId).list();
	}


	public void add(Comments comment) {
		Session session=sessionFactory.openSession();
		int newidno1 =session.createQuery("from Comments").list().size() +1;
		String id1 ="C"+newidno1;
		comment.setId(id1);
		comment.setCommentedAt(new Date(System.currentTimeMillis()));
		this.sessionFactory.getCurrentSession().saveOrUpdate(comment);
	}


	public void remove(Comments comment) {
		((CommentDAO) this.sessionFactory.getCurrentSession()).remove(comment);
	}

}
