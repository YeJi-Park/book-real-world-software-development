package com.iteratrlearning.shu_book.chapter_06;

public class SenderEndPoint {
	
	private final User user;
	private final Twootr twootr;
	
	public SenderEndPoint(User user, Twootr twootr) {
		this.user = user;
		this.twootr = twootr;
	}
	
	public FollowStatus onFollow(String idToFollow) {
		return twootr.onFollow(user, idToFollow);
	}
}
