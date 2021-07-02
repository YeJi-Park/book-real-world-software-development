package com.iteratrlearning.shu_book.chapter_05;

public class Main {

	public static void main(String[] args) {
		Facts env = new Facts();
		
		BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(env);
		
		businessRuleEngine.addAction(facts->{
			var forecastedAmount = 0.0;
			var dealStage = Stage.valueOf(facts.getFact("stage"));
			var amount = Double.parseDouble(facts.getFact("amount"));
			
			switch(dealStage) {
			 case LEAD:
				 forecastedAmount = amount * 0.2;
				 break;
			 case EVALUATING:
				 forecastedAmount = amount * 0.5;
				 break;
			 case INTERESTED:
				 forecastedAmount = amount * 0.8;
				 break;
			 case CLOSED:
				 forecastedAmount = amount;
				 break;
			}
			
//			var forecastedAmount = amount * switch (dealStage) {
//			 case LEAD -> 0.2;
//			 case EVALUATING -> 0.5;
//			 case INTERESTED -> 0.8;
//			 case CLOSED -> 1;
//			}
			
			facts.addFact("forecastedAmount", String.valueOf(forecastedAmount));
		});
	}

}
