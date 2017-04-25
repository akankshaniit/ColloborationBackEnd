package com.niit.collaboration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="c_chatforum")
public class ChatForumComment {

	@Id
	@Column
	private String id;
	@Column
	private String forum_id;
	@Column
	private String user_id;
	@Column
	private String message;
	@Column(name="commented_date")
	private String commentedDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getForum_id() {
		return forum_id;
	}
	public void setForum_id(String forum_id) {
		this.forum_id = forum_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCommentedDate() {
		return commentedDate;
	}
	public void setCommentedDate(String commentedDate) {
		this.commentedDate = commentedDate;
	}
	
}
