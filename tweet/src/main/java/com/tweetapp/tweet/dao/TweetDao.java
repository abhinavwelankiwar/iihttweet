package com.tweetapp.tweet.dao;

import java.util.List;

import com.tweetapp.tweet.entity.Tweet;
import com.tweetapp.tweet.entity.User;

public interface TweetDao {

	Tweet saveTweet(Tweet tweet);
	List<Tweet> getTweetByUserId(User user);
	List<Tweet> getAllTweets();
}
