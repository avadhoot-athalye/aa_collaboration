package com.codehub.webapp.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codehub.webapp.dao.ForumDAO;
import com.codehub.webapp.dao.ForumPostDAO;
import com.codehub.webapp.dao.ForumRequestDAO;
import com.codehub.webapp.dao.UserDAO;
import com.codehub.webapp.entity.Blog;
import com.codehub.webapp.entity.BlogComments;
import com.codehub.webapp.entity.Forum;
import com.codehub.webapp.entity.ForumPosts;
import com.codehub.webapp.entity.ForumRequest;
import com.codehub.webapp.entity.User;

@RestController
public class ForumController {

	@Autowired
	ForumDAO forumDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	Forum forum;
	
	@Autowired
	ForumPostDAO forumPostDAO;
	
	@Autowired
	ForumRequestDAO forumRequestDAO;
	
	//Method for creating new forum category
	@RequestMapping(value = {"/forum/new"}, method = RequestMethod.POST)
	public ResponseEntity<Forum> addForumCategory(@RequestBody Forum forum) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now(); 
		forum.setPostDate(LocalDate.parse(dtf.format(now)));
		forum.setStatus("APPROVED");
		forum.setNoOfPosts(0);
		User user = null;	//creating instance of user
		int id = forum.getUserId();	//retrieving user id from forum
		user = userDAO.getUser(id);	//fetching user detail by its id
		forumDAO.addForum(forum);
		int forumId = forum.getId();
		ForumRequest fr = new ForumRequest();
		fr.setUserId(id);
		fr.setUsername(user.getUsername());
		fr.setStatus("APPROVED");
		fr.setForum(forum);
		forumRequestDAO.addForumRequest(fr);
		
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	//Method for fetching list of all forum categories
	@RequestMapping(value = {"/forum/list"}, method = RequestMethod.GET)
	public ResponseEntity<List<Forum>> fetchForums() {
		System.out.println("Method called");
		List<Forum> forums = forumDAO.list();
		return new ResponseEntity<List<Forum>>(forums, HttpStatus.OK);
	}
	
	
	//Method for viewing single blog using blog id as a parameter
	
	@RequestMapping(value = {"/forum/{id}"}, method = RequestMethod.GET)
	public ResponseEntity<Forum> viewForum(@PathVariable("id") int id) {
		System.out.println("Calling method");
		Forum forum = new Forum();
		forum = forumDAO.getForum(id);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
			
		}
	
	@RequestMapping(value = {"/forum/post/new/{id}"}, method = RequestMethod.POST)
	public ResponseEntity<ForumPosts> addForumPost(@PathVariable("id") int id, @RequestBody ForumPosts forumPosts) {
		System.out.println("Adding forum post now");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now(); 
		forumPosts.setPostDate(LocalDate.parse(dtf.format(now)));
		forum = forumDAO.getForum(id);
		forum.setNoOfPosts(forum.getNoOfPosts() + 1);
		forumDAO.updateForum(forum);
		forumPosts.setForum(forum);
		forumPostDAO.addForumPosts(forumPosts);
		
		return new ResponseEntity<ForumPosts>(forumPosts, HttpStatus.OK);	
	}
	
	 //Function to fetch forum post list
	 @RequestMapping(value = {"/forum/posts/list/{id}"}, method = RequestMethod.GET)
	 public ResponseEntity<List<ForumPosts>> fetchForumPosts(@PathVariable("id") int id) {
			System.out.println("fetching list of forum posts now");
			List<ForumPosts> forumPosts = forumPostDAO.list(id);
			return new ResponseEntity<List<ForumPosts>>(forumPosts, HttpStatus.OK);
	}
	
}
