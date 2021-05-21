package com.iteratrlearning.shu_book.chapter_02;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.iteratrlearning.shu_book.chapter_03.BankTransactionFilter;
import com.iteratrlearning.shu_book.chapter_03.BankTransactionSummarizer;

public class BankStatementProcessor {
private final List<BankTransaction> bankTransactions;
	
	public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
		this.bankTransactions = bankTransactions;
	}
	
	public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
		double result = 0;
		for(final BankTransaction bankTransaction: bankTransactions) {
			result = bankTransactionSummarizer.summarize(result, bankTransaction);
		}
		
		return result;
	}
	
	public double calculateTotalAmount() {
		double total = 0;
		for(final BankTransaction bankTransaction: bankTransactions) {
			total += bankTransaction.getAmount();
		}
		
		return total;
	}
	
	public double calculateTotalInMonth(final Month month) {
		return summarizeTransactions((acc, bankTransaction) -> 
		bankTransaction.getDate().getMonth() == month ?
				bankTransaction.getAmount() + acc : acc
		);
	}
	
	public double calculateTotalForCategory(final String category) {
		return summarizeTransactions((acc, bankTransaction) -> 
		bankTransaction.getDescription().equals(category)?
				bankTransaction.getAmount() + acc : acc
		);
	}
	
	public double getMaxAmountInDateRange(final LocalDate from, final LocalDate to) {
		double maxAmount = Double.MIN_VALUE;
		for(final BankTransaction bankTransaction: bankTransactions) {
			if( (bankTransaction.getDate().isEqual(from) || bankTransaction.getDate().isAfter(from) ) 
					&& (bankTransaction.getDate().isEqual(to) || bankTransaction.getDate().isBefore(to)) ) {
				maxAmount = Double.max(maxAmount, bankTransaction.getAmount());
			}
		}
		
		return maxAmount;
	}
	
	public double getMinAmountInDateRange(final LocalDate from, final LocalDate to) {
		double minAmount = Double.MAX_VALUE;
		for(final BankTransaction bankTransaction: bankTransactions) {
			if( (bankTransaction.getDate().isEqual(from) || bankTransaction.getDate().isAfter(from) ) 
					&& (bankTransaction.getDate().isEqual(to) || bankTransaction.getDate().isBefore(to)) ) {
				minAmount = Double.min(minAmount, bankTransaction.getAmount());
			}
		}
		
		return minAmount;
	}
	
	public Map<Month, Double> getAmountGroupingByMonth() {
		Map<Month, Double> monthHistogram = new TreeMap<>();
		
		for(Month month : Month.values()) {
			monthHistogram.put(month, 0d);
		}
		
		for(final BankTransaction bankTransaction: bankTransactions) {
			monthHistogram.computeIfPresent(bankTransaction.getDate().getMonth(), 
					(k,v)-> v = v + bankTransaction.getAmount());
		}
		
		return monthHistogram; 
	}
	
	public Map<String, Double> getAmountGroupingByCategory(){
		return bankTransactions.stream()
						.collect(Collectors.groupingBy(
									BankTransaction::getDescription, 
									Collectors.summingDouble(BankTransaction::getAmount)));					
	}
	

	public List<BankTransaction> findTranactions(final BankTransactionFilter bankTransactionFilter){
		final List<BankTransaction> result = new ArrayList<>();
		
		for(final BankTransaction bankTransaction: bankTransactions) {
			if(bankTransactionFilter.test(bankTransaction)) {
				result.add(bankTransaction);
			}
		}
		
		return result;
	}
	
	public List<BankTransaction> findTransactionsGreatereThanEqual(final int amount){
		return findTranactions(bankTransaction ->
			bankTransaction.getAmount() >= amount );
	}
}
