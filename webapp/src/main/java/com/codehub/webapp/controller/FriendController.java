package com.codehub.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codehub.webapp.dao.FriendsDAO;
import com.codehub.webapp.dao.UserDAO;
import com.codehub.webapp.entity.Blog;
import com.codehub.webapp.entity.Forum;
import com.codehub.webapp.entity.ForumRequest;
import com.codehub.webapp.entity.Friends;
import com.codehub.webapp.entity.User;

@RestController
public class FriendController {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	FriendsDAO friendsDAO; 
	
		//Method to send friend request
		@RequestMapping(value = {"/user/friendRequest/{id}"}, method = RequestMethod.POST)
		public ResponseEntity<Friends> sendFriendRequest(@PathVariable("id") int id, @RequestBody Integer initId) {
				System.out.println("Sending friend request now!");
				Friends friends = new Friends();
				User user = userDAO.getUser(id); //Fetching friend by friend id
				friends.setFriendId(id);
				friends.setInitiatorId(initId);
				friends.setStatus("PENDING");
				friendsDAO.addFriend(friends);
				return new ResponseEntity<Friends>(friends, HttpStatus.OK);
			}
		
		//Method to fetch friend requests
		@RequestMapping(value = {"/user/friendRequest/list/{id}"}, method = RequestMethod.GET)
		public ResponseEntity<List<User>> fetchRequest(@PathVariable("id") int userId) {
				System.out.println("Fetchng list of friend request received");
				List<Friends> friends = friendsDAO.list(userId);
				List<User> users = new ArrayList<>();
				for(Friends fr : friends) {
					User user = userDAO.getUser(fr.getInitiatorId());
					users.add(user);
				}
				return new ResponseEntity<List<User>>(users, HttpStatus.OK);
			}
	
}