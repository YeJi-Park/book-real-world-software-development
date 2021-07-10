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
		Optional<SenderEndPoint> res = Optional.empty();
		User sameId = userRepository.get(userId); 
		if( sameId != null) {
			byte[] hashedPassword = KeyGenerator.hash(password, sameId.getSalt());
			Arrays.equals(sameId.getPassword(), hashedPassword);
		}
			
		return res;
	}
	
	public boolean onRegisterUser(String userId, String password) {
		byte[] salt = KeyGenerator.newSalt();
		final User newUser = new User(userId, KeyGenerator.hash(password, salt), salt);
		return userRepository.put(userId, newUser)!=null;
	}
}
