package com.tweetapp.tweet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.tweet.dao.UserDao;
import com.tweetapp.tweet.entity.User;
import com.tweetapp.tweet.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public User registerUser(final User user) {
		return userDao.registerUser(user);
	}

	@Override
	public User getUserByUserId(final String userId) {
		return userDao.getUserByUserId(userId);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	

}
