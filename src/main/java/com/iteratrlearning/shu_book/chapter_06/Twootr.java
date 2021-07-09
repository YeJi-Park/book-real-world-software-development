package com.iteratrlearning.shu_book.chapter_06;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Twootr {
	
	private final Map<String, User> userRepository;
	
	public Twootr(Map<String, User> userRepository ) {
		this.userRepository = userRepository;
	}
	
	public Optional<SenderEndPoint> onLogon(String userId, String password, ReceiverEndPoint receiver){
		Optional<SenderEndPoint> res = Optional.empty();
		if( userRepository.get(userId) != null) {
			res = Optional.of(new SenderEndPoint());
		}
			
		return res;
	}
}
