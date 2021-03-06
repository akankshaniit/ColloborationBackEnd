package com.niit.collaboration.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="c_blog")
public class Blog extends BaseDomain {

	@Id
	@Column
	private String id;
	@Column
	private String title;
	@Column
	private String user_id;
	
	@Column
	@DateTimeFormat(pattern = "dd MMM, yyyy HH:mm:ss")
	
	private Date date_time;
	@Column(nullable=true, name="status")
    private Character status;
	@Column
	
	private String reason;
	@Column
	
	private String description;
	
	
	public Date getDate_time() {
		return date_time;
	}
	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
