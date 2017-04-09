package com.codehub.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codehub.webapp.dao.BlogDAO;
import com.codehub.webapp.dao.ForumRequestDAO;
import com.codehub.webapp.dao.UserDAO;
import com.codehub.webapp.entity.Blog;
import com.codehub.webapp.entity.ForumRequest;
import com.codehub.webapp.entity.User;

@RestController
public class RequestsController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	ForumRequestDAO forumRequestDAO;
	
	@Autowired
	BlogDAO blogDAO;

			//Method for fetching pending user list by status
			@RequestMapping(value = {"/user/request/list"}, method = RequestMethod.GET)
			public ResponseEntity<List<User>> fetchPendingUsers() {
				System.out.println("fetching list of pending users");
				List<User> user = userDAO.list("PENDING");
				return new ResponseEntity<List<User>>(user, HttpStatus.OK);
			}
			
			
			//Method to change user registration status
			@RequestMapping(value = {"/user/request/approval/{id}"}, method = RequestMethod.POST)
			public ResponseEntity<User> changeStatus(@PathVariable("id") int id) {
					System.out.println("changing status");
					User user = new User();
					user = userDAO.getUser(id);
					user.setStatus("APPROVED");
					userDAO.updateUser(user);
					return new ResponseEntity<User>(user, HttpStatus.OK);
			}
			
			//Method for fetching list of all forum request with pending status
			@RequestMapping(value = {"/forum/request/list"}, method = RequestMethod.GET)
			public ResponseEntity<List<ForumRequest>> fetchForumRequests() {
				System.out.println("Method called");
				List<ForumRequest> forumsRequests = forumRequestDAO.list("PENDING");
				return new ResponseEntity<List<ForumRequest>>(forumsRequests, HttpStatus.OK);
			}
			
			//Method to change forum request status
			@RequestMapping(value = {"/forum/request/approval/{id}"}, method = RequestMethod.POST)
			public ResponseEntity<ForumRequest> changeFRStatus(@PathVariable("id") int id) {
				 	System.out.println("changing status");
					ForumRequest forumRequest = new ForumRequest();
					forumRequest = forumRequestDAO.getForumRequest(id);
					forumRequest.setStatus("APPROVED");
					forumRequestDAO.updateForumRequest(forumRequest);
							return new ResponseEntity<ForumRequest>(forumRequest, HttpStatus.OK);
					}

			//Method for fetching pending blog list by status
			@RequestMapping(value = {"/blog/request/list"}, method = RequestMethod.GET)
			public ResponseEntity<List<Blog>> fetchPendingBlogs() {
					System.out.println("fetching list of pending blogs");
					List<Blog> blog = blogDAO.getBlogsByStatus("PENDING");
						return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
			}
			
			//Method to change blog request status
			@RequestMapping(value = {"/blog/request/approval/{id}"}, method = RequestMethod.POST)
			public ResponseEntity<Blog> changeBlogStatus(@PathVariable("id") int id) {
					System.out.println("changing blog status");
					Blog blog = null;
					blog = blogDAO.getBlog(id);
					blog.setStatus("APPROVED");
					blogDAO.updateBlog(blog);
					return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			}
}