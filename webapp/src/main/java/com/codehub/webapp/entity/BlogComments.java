package com.codehub.webapp.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="BLOG_COMMENT")
public class BlogComments implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 434814845183284736L;
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="BLOG_COMMENT_SEQ", allocationSize=1)
	@Column(name="Blog_Comment_Id")
	private int id;
	
	@Column(name="User_Id")
	private int userId;
	
	@Column(name="User_Name")
	private String username;
	
	@Column(name="Blog_Comment")
	private String blogComment;
	
	@Column(name="Comment_Date")
	private LocalDate commentDate;
	
	@Column(name="No_Of_Likes")
	private int noOfLikes;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Blog_Id")
	private Blog blog;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBlogComment() {
		return blogComment;
	}

	public void setBlogComment(String blogComment) {
		this.blogComment = blogComment;
	}

	public LocalDate getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(LocalDate commentDate) {
		this.commentDate = commentDate;
	}

	public int getNoOfLikes() {
		return noOfLikes;
	}

	public void setNoOfLikes(int noOfLikes) {
		this.noOfLikes = noOfLikes;
	}
	
	
}