package com.iteratrlearning.shu_book.chapter_05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
}
