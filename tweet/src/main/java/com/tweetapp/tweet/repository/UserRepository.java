package com.tweetapp.tweet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.tweet.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
