package com.iteratrlearning.shu_book.chapter_06;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class User {
	private final String id;
	private final byte[] password;
	private final byte[] salt;
	
	private final Set<User> followers = new HashSet<>();
	private final Set<String> following = new HashSet<>();
	
	public User(String id, byte[] password, byte[] salt) {
		this.id = id;
		this.password = password;
		this.salt = salt;
	}

	public String getId() {
		return id;
	}

	public byte[] getPassword() {
		return password;
	}
	
	public byte[] getSalt() {
		return salt;
	}
	
	public Stream<User> followers() {
		return followers.stream();
	}
	
	public FollowStatus addFollower(User user) {
		if(followers.add(user)) {
			user.following.add(id);
			return FollowStatus.SUCCESS;
		}else {
			return FollowStatus.ALREADY_FOLLOWING;
		}
	}
	
	public Set<String> getFollowing() {
		return following;
	}
}
