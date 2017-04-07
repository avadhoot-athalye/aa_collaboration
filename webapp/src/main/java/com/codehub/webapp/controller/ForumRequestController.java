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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codehub.webapp.dao.ForumDAO;
import com.codehub.webapp.dao.ForumRequestDAO;
import com.codehub.webapp.dao.UserDAO;
import com.codehub.webapp.entity.Forum;
import com.codehub.webapp.entity.ForumRequest;
import com.codehub.webapp.entity.User;

@RestController
public class ForumRequestController {

	@Autowired
	ForumRequestDAO forumRequestDAO;
	
	@Autowired
	ForumDAO forumDAO;
	
	@Autowired
	UserDAO userDAO;
	
		//Method to send forum join request
		@RequestMapping(value = {"/forum/request/{id}"}, method = RequestMethod.POST)
		public ResponseEntity<ForumRequest> addForumRequest(@PathVariable("id") int id, @RequestBody Integer forumId) {
			System.out.println("Success!");
			ForumRequest forumRequest = new ForumRequest();
			Forum forum = null;
			User user = null;
			user = userDAO.getUser(id); //Fetching user by user id
			forumRequest.setUserId(id);
			String username = user.getUsername();
			forumRequest.setUsername(username);
			forum = forumDAO.getForum(forumId); //Fetching forum with forum Id
			forumRequest.setForum(forum);
			if( user.getRole().equals("Super_Admin") || user.getRole().equals("Admin") ) {
				forumRequest.setStatus("APPROVED");
			} else {
				forumRequest.setStatus("PENDING");
			}
			
			forumRequestDAO.addForumRequest(forumRequest);
			return new ResponseEntity<ForumRequest>(forumRequest, HttpStatus.OK);
		}
		
		//Method for fetching list of all forum request with pending status
		@RequestMapping(value = {"/forum/request/list"}, method = RequestMethod.GET)
		public ResponseEntity<List<ForumRequest>> fetchForumRequests() {
			System.out.println("Method called");
			List<ForumRequest> forumsRequests = forumRequestDAO.list("PENDING");
			return new ResponseEntity<List<ForumRequest>>(forumsRequests, HttpStatus.OK);
		}
		
		//Method for fetching list of participated users
		@RequestMapping(value = {"/forum/participatedUsers/list/{id}"}, method = RequestMethod.GET)
		public ResponseEntity<List<ForumRequest>> fetchParticipatedUsers(@PathVariable("id") int id) {
			System.out.println("Fetching list of users");
			List<ForumRequest> forumRequests = forumRequestDAO.list(id);
			return new ResponseEntity<List<ForumRequest>>(forumRequests, HttpStatus.OK);
		}
}
