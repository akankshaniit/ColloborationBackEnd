package com.niit.collaboration.model;





import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="c_chat")
public class Chat extends BaseDomain {

	@Id
	@Column
	private String id;
	@Column
	private String user_id;
	@Column
	private String friend_id;
	@Column(name="date_time")
	private Date dateTime;
	@Column
	private String message;
	@Column(name="comment_date")
	private Date commented_date;
	
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = new Date();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCommented_date() {
		return commented_date;
	}
	public void setCommented_date(Date commented_date) {
		this.commented_date = new Date();
	}
	
	
}
