package com.tweetapp.tweet;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.tweetapp.tweet.entity.Tweet;
import com.tweetapp.tweet.entity.User;
import com.tweetapp.tweet.exception.IncorrectPasswordException;
import com.tweetapp.tweet.exception.UserNotFoundException;
import com.tweetapp.tweet.service.TweetService;
import com.tweetapp.tweet.service.UserService;

@SpringBootApplication
public class TweetApplication{

	private static final Logger LOGGER = LoggerFactory.getLogger(TweetApplication.class);

	@Autowired
	private UserService userService;

	@Autowired
	private TweetService tweetService;

	public static void main(String[] args) {
		SpringApplication.run(TweetApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			LOGGER.info("Application started");
			final Scanner scanner = new Scanner(System.in);

			
				String choice = "";
				String insideChoice = "";
				do {
					choice = "";
					try {
						
					System.out.println("Enter\n"
							+ "1.Register\n"
							+ "2.Login\n"
							+ "3.Forgot Password\n"
							+ "4.Exit");
					choice = scanner.nextLine();
					User user = new User();

					switch (choice) {
					case "1":
						System.out.println("Enter Userid (email)");
						user.setUserId(scanner.nextLine());
						System.out.println("Enter Name:");
						user.setName(scanner.nextLine());
						System.out.println("Enter Password");
						user.setPassword(scanner.nextLine());
						this.userService.registerUser(user);
						break;
					case "2":
						System.out.println("Enter Userid (email)");
						final String userId = scanner.nextLine();

						System.out.println("Enter Password");

						String password = scanner.nextLine();
						user = this.userService.getUserByUserId(userId);
						if (user == null) {
							throw new UserNotFoundException("Incorrect credentials");
						}
						if (user.getPassword().equals(password)) {
							user.setLoggedIn(true);
							this.userService.registerUser(user);
							do {
								
								System.out.println(
										"1.Post a Tweet\n"
										+ "2.View my tweets\n"
										+ "3.View all tweets\n"
										+ "4.View all users\n"
										+ "5.Reset password\n"
										+ "6.Logout");
								insideChoice = scanner.nextLine();
								switch (insideChoice) {
								case "1":
									final Tweet tweet = new Tweet();
									tweet.setUserId(user);
									System.out.println("Enter tweet description");
									tweet.setTweetDescription(scanner.nextLine());
									tweetService.saveTweet(tweet);
									break;
								case "2":
									List<Tweet> tweets = this.tweetService.getTweetByUserId(user);
									tweets.stream().forEach((tweetElement) -> {
										System.out.println("Tweet: "+tweetElement.getTweetDescription());
									});
									break;
								case "3":
									tweets = tweetService.getAllTweets();
									tweets.stream().forEach((tweetElement) -> {
										System.out.println("Tweet: "+tweetElement.getTweetDescription());
									});
									break;
								case "4":
									List<User> users = this.userService.getAllUsers();
									users.stream().forEach((userElement) -> {
										System.out.println(userElement.getName());
									});
									break;
								case "5":
									System.out.println("Enter Current Password");
									String currentPassword = scanner.nextLine();
									if (user.getPassword().equalsIgnoreCase(currentPassword.trim())) {
										System.out.println("Enter new Password");
										String newPassword = scanner.nextLine();
										user.setPassword(newPassword);
										this.userService.registerUser(user);
										System.out.println("You have successfully reset your password");
									} else {
										throw new IncorrectPasswordException("Incorrect credentials");
									}

									break;
								case "6":
									user.setLoggedIn(false);
									System.out.println();
									System.out.println("Logged Out");
									System.out.println();
									this.userService.registerUser(user);
									break;
								default:
									break;
								}
							} while (!insideChoice.equals("6"));
						} else {
							throw new IncorrectPasswordException("Incorrect credentials");
						}
						break;
					case "3":
						System.out.println("Enter Userid");
						user = this.userService.getUserByUserId(scanner.nextLine());
						if (user == null) {
							throw new UserNotFoundException("User not found");
						}
						System.out.println("Enter New Password");
						user.setPassword(scanner.nextLine());
						this.userService.registerUser(user);
						System.out.println("You have successfully changed your password");
						break;
					default:
						break;
					}
					}
					catch(Exception e) {
						LOGGER.error("Exception :"+ e.getMessage());
					}
				} while (!choice.equals("4"));
				scanner.close();


		};
	}
	

}
