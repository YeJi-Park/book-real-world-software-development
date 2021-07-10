package com.iteratrlearning.shu_book.chapter_06;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class Twootr {
	
	private final Map<String, User> userRepository;
	
	public Twootr(Map<String, User> userRepository ) {
		this.userRepository = userRepository;
	}
	
	public Optional<SenderEndPoint> onLogon(String userId, String password, ReceiverEndPoint receiver){
		Optional<User> user = Optional.ofNullable(userRepository.get(userId));
		
		user.filter(sameIdUser->{
			byte[] hashedPassword = KeyGenerator.hash(password, sameIdUser.getSalt());
			return Arrays.equals(sameIdUser.getPassword(), hashedPassword);
		});
			
		return user.map(authenticatedUser -> new SenderEndPoint(authenticatedUser, this));
	}
	
	public boolean onRegisterUser(String userId, String password) {
		byte[] salt = KeyGenerator.newSalt();
		final User newUser = new User(userId, KeyGenerator.hash(password, salt), salt);
		return userRepository.put(userId, newUser)!=null;
	}
	
	public FollowStatus onFollow(User user, String idToFollow) {
		return Optional.ofNullable(userRepository.get(idToFollow))
				.map(followee -> followee.addFollower(user))
				.orElse(FollowStatus.INVALID_USER);
	}
}
