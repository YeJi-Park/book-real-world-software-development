package com.iteratrlearning.shu_book.chapter_06;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
public class TwootrTest {
	
	private Twootr twootr;
	private SenderEndPoint endPoint;
	
	private final Map<String, User> userRepository = new HashMap<>();
	private final ReceiverEndPoint receiverEndPoint = Mockito.mock(ReceiverEndPoint.class);
	
	@BeforeAll
	public void setUp() {
		twootr = new Twootr(userRepository);
		twootr.onRegisterUser(TestData.USER_ID, TestData.PASSWORD);
	}

	@Test
	public void shouldBeAbleToAuthenticateUser() {
		logon();
	}
	
	@Test
	public void shouldNotAuthenticateUnknownUser() {
		final Optional<SenderEndPoint> endPoint = twootr.onLogon(
				TestData.NOT_A_USER, "Bad password", receiverEndPoint);
		
		assertFalse(endPoint.isPresent());
	}
	
	@Test
	public void shouldFollowValidUser() {
		logon();
		
		final FollowStatus followStatus = endPoint.onFollow(TestData.OTHER_USER_ID);
		assertEquals(FollowStatus.SUCCESS, followStatus);
	}
	
	@Test
	public void shouldNotDuplicateFollowValidUser() {
		logon();
		
		endPoint.onFollow(TestData.OTHER_USER_ID);
		final FollowStatus followStatus = endPoint.onFollow(TestData.OTHER_USER_ID);
		assertEquals(FollowStatus.ALREADY_FOLLOWING, followStatus);
	}
	
	private void logon() {
		this.endPoint = logon(TestData.USER_ID, receiverEndPoint);
	}
	
	private SenderEndPoint logon(String userId, ReceiverEndPoint receiverEndPoint) {
		Optional<SenderEndPoint> endPoint = twootr.onLogon(userId, TestData.PASSWORD, receiverEndPoint);
		assertTrue(endPoint.isPresent());
		
		return endPoint.get();
	}
	
	
}
