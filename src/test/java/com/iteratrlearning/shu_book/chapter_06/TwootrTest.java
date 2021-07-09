package com.iteratrlearning.shu_book.chapter_06;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestInstance(Lifecycle.PER_CLASS)
public class TwootrTest {
	
	private Twootr twootr;
	private final Map<String, User> userRepository = new HashMap<>();
	
	private final ReceiverEndPoint receiverEndPoint = Mockito.mock(ReceiverEndPoint.class);
	
	@BeforeAll
	public void setUp() {
		twootr = new Twootr(userRepository);
		userRepository.put(TestData.USER_ID, new User(TestData.USER_ID, TestData.PASSWORD));
		userRepository.put(TestData.USER_ID, new User(TestData.USER_ID, TestData.PASSWORD));
	}

	@Test
	public void shouldBeAbleToAuthenticateUser() {
		final Optional<SenderEndPoint> endpoint =  twootr.onLogon(
				TestData.USER_ID, TestData.PASSWORD, receiverEndPoint);
		
		assertFalse(endpoint.isEmpty());
	}
	
	@Test
	public void shouldNotAuthenticateUnknownUser() {
		final Optional<SenderEndPoint> endPoint = twootr.onLogon(
				TestData.NOT_A_USER, "Bad password", receiverEndPoint);
		
		assertFalse(endPoint.isPresent());
	}
}
