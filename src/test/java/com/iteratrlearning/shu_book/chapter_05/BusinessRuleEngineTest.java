package com.iteratrlearning.shu_book.chapter_05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class BusinessRuleEngineTest {
	@Test
	void shouldHaveNoRulesInitially() {
		final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();
		assertEquals(0, businessRuleEngine.count());
	}
	
	@Test
	void shouldAddTwoAction() {
		final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();
		
		businessRuleEngine.addAction(() -> {});
		businessRuleEngine.addAction(() -> {});
		
		assertEquals(2, businessRuleEngine.count());
	}
	
	@Test
	void shouldExecuteOneAction() {
		final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();
		final Action mockAction = mock(Action.class); // mock 객체 생성
		
		businessRuleEngine.addAction(mockAction);
		businessRuleEngine.run(); // when
		
		verify(mockAction).execute(); // then
	}
	
	@Test
	public void shouldPerformAnActionWithFacts() {
		final Action mockAction = mock(Action.class);
		final Facts mockFacts = mock(Facts.class);
		final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockedFacts);
		
		businessRuleEngine.addAction(mockAction);
		businessRuleEngine.run();
		
		verify(mockAction).execute(mockFacts);
	}
}
