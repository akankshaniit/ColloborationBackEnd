package com.niit.collaboration.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name = "blog_comment")
public class Comments extends BaseDomain {
	
	@Id 
	private String id;
	
	@Column(name="content")
	private String content;
	
	@Column(name = "blog_id")
	private String blog_id;
	
	@Column(name = "user_id") 
	private String user_id;
	
	@DateTimeFormat(pattern = "dd MMM, yyyy HH:mm:ss")
	@Column
	private Date commentedAt;

	
	public Comments() {
		
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(String blogId) {
		this.blog_id = blogId;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String userId) {
		this.user_id = userId;
	}

	public Date getCommentedAt() {
		return commentedAt;
	}

	public void setCommentedAt(Date commentedAt) {
		this.commentedAt = commentedAt;
	}
		
}