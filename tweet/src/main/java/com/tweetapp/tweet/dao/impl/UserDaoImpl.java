package com.tweetapp.tweet.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.tweet.dao.UserDao;
import com.tweetapp.tweet.entity.User;
import com.tweetapp.tweet.repository.UserRepository;

@Service
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(final User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserByUserId(final String userId) {

		Optional<User> findById = userRepository.findById(userId);
		return findById.isPresent() ? findById.get() : null;

	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
