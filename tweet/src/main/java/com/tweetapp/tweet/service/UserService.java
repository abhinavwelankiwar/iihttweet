package com.tweetapp.tweet.service;

import java.util.List;

import com.tweetapp.tweet.entity.User;

public interface UserService {
	
	User registerUser(User user);
	User getUserByUserId(String userId);
	List<User> getAllUsers();

}
