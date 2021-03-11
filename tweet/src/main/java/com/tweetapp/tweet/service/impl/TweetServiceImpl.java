package com.tweetapp.tweet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.tweet.dao.TweetDao;
import com.tweetapp.tweet.entity.Tweet;
import com.tweetapp.tweet.entity.User;
import com.tweetapp.tweet.service.TweetService;

@Service
public class TweetServiceImpl implements TweetService{

	@Autowired
	private TweetDao tweetDao;
	
	@Override
	public Tweet saveTweet(Tweet tweet) {
		return tweetDao.saveTweet(tweet);
	}

	@Override
	public List<Tweet> getTweetByUserId(User user) {
		return tweetDao.getTweetByUserId(user);
	}

	@Override
	public List<Tweet> getAllTweets() {
		return tweetDao.getAllTweets();
	}

}
