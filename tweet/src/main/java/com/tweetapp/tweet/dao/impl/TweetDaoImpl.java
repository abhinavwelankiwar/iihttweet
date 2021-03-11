package com.tweetapp.tweet.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.tweet.dao.TweetDao;
import com.tweetapp.tweet.entity.Tweet;
import com.tweetapp.tweet.entity.User;
import com.tweetapp.tweet.repository.TweetRepository;

@Service
public class TweetDaoImpl implements TweetDao{

	@Autowired
	private TweetRepository tweetRepository;
	
	@Override
	public Tweet saveTweet(final Tweet tweet) {
		return tweetRepository.save(tweet);
	}

	@Override
	public List<Tweet> getTweetByUserId(final User user) {
		return tweetRepository.findTweetsByUserId(user);
	}

	@Override
	public List<Tweet> getAllTweets() {
		return tweetRepository.findAll();
	}

}
