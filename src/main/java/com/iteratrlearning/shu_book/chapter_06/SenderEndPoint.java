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
	
	public void onSendTwoot(String id, String content) {
		final String userId = user.getId();
		 final Twoot twoot = new Twoot(id, userId, content);
		 user.followers()
		 	.filter(User::isLoggedOn)
		 	.forEach(follower -> follower.receiveTwoot(twoot));
	}
}
