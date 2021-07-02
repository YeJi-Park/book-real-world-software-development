package com.iteratrlearning.shu_book.chapter_05;

public class RuleBuilder {
	private Condition condition;
	
	public static RuleBuilder when(final Condition condition) {
		return new RuleBuilder(condition);
	}
	
	public Rule then(final Action action) {
		return new DefaultRule(condition, action);
	}
	
	private RuleBuilder(final Condition condition) {
		this.condition = condition;
	}
}
