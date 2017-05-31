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

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.model.Friend;

@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger log = LoggerFactory.getLogger(FriendDAOImpl.class);
	private static Logger log1 = LoggerFactory.getLogger(Friend.class);
	
	@Override
	public Friend get(String userID) {
		String hql = "from Friend where user_id=" + "'" + userID + "' or friend_id= '" + userID + "'";
		log.debug("hql: " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
log.debug(userID);
		return (Friend) query.uniqueResult();
	}
	@Override
	public List<Friend> list() {
		System.out.println("hiii");
		List<Friend> myFriends=(List<Friend>)sessionFactory.openSession().createQuery("from Friend").list();
		System.out.println(myFriends);
		return myFriends;
	}

	@Override
	public boolean save(Friend friend) {
		try {
			Session session=sessionFactory.openSession();
			int newidno2 =session.createQuery("from Friend").list().size() +1;
			String id ="F"+newidno2;
			friend.setId(id);
			
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
	@Override
	public Friend get(String userID, String friendID) {
		String hql = "from Friend where user_id=" + "'" + userID + "' and friend_id= '" + friendID + "'";

		log.debug("hql: " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);

		return (Friend) query.uniqueResult();
	}
	@Override
	public List<Friend> getMyFriends(String userID) {
		/*
		 * select user_id from c_friend where user_id='Srinivas' and status ='A'
		 * UNION select friend_id from c_friend where user_id='Srinivas' and
		 * status ='A' MINUS select user_id from c_friend where
		 * user_id='Srinivas';
		 */
		String hql1 = "select friend_id  from Friend where user_id='" + userID + "' and status = 'A' ";

				/*+ " union  " +*/

		String hql2= "select user_id from Friend where friend_id='" + userID + "' and status = 'A'";

		log.debug("getMyFriends hql1 : " + hql1);
		log.debug("getMyFriends hql2 : " + hql2);
	
		List<Friend> list1 = sessionFactory.openSession().createQuery(hql1).list();
		List<Friend> list2 = sessionFactory.openSession().createQuery(hql2).list();
		
		
		
		list1.addAll(list2);
       
		return list1;
		
	}
	@Override
	public void delete(String userID, String friendID) {
		Friend friend = new Friend();
		friend.setFriend_id(friendID);
		friend.setUser_id(userID);
		sessionFactory.openSession().delete(friend);
	}
	@Override
	public List<Friend> getNewFriendRequests(String userID) {
		String hql = "from Friend where user_id=" + "'" + userID + "' and status ='" + "N'";

		log.debug(hql);
		 return  sessionFactory.openSession().createQuery(hql).list();

	}
	@Override
	public void setOnline(String friendID) {
		log.debug("Starting of the metnod setOnline");
		//String hql = " UPDATE Friend	SET isOnline = 'Y' where friendID='" + friendID + "'";
		
		String hql = " UPDATE Friend	SET isOnline = 'Y' where friend_id= ?";
		
		
		
		
		log.debug("hql: " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
		query.setString(0, friendID);
		
		query.executeUpdate();
		log.debug("Ending of the metnod setOnline");

		
	}
	@Override
	public void setOffLine(String friendID) {
		log.debug("Starting of the metnod setOffLine");
		String hql = " UPDATE Friend	SET isOnline = 'N' where friend_id='" + friendID + "'";
		log.debug("hql: " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
		query.executeUpdate();
		log.debug("Ending of the metnod setOffLine");

	}
	@Override
	public List<Friend> getRequestsSendByMe(String userID) {
		String hql = "select friendID from Friend where user_id=" + "'" + userID + "' and status ='" + "N'";

		log.debug(hql);
		return  sessionFactory.openSession().createQuery(hql).list();

	}
	
	

}
