package com.tweetapp.tweet.dao;

import java.util.List;

import com.tweetapp.tweet.entity.User;

public interface UserDao {
	
	User registerUser(User user);
	User getUserByUserId(String userId);
	List<User> getAllUsers();

}
