package com.iteratrlearning.shu_book.chapter_05;

public class Main {

	public static void main(String[] args) {
		Facts env = new Facts();
		
		BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(env);
		
		businessRuleEngine.addAction(facts->{
			var jobTitle = facts.getFact("jobTitle");
			if("CEO".equals(jobTitle)) {
				var name = facts.getFact("name");
				 Mailer.sendEmail("sales@company.com", "Relevant customer: "+name);
			}
		});
	}

}
