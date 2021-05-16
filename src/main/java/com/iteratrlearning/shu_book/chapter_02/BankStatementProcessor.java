package com.iteratrlearning.shu_book.chapter_02;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class BankStatementProcessor {
private final List<BankTransaction> bankTransactions;
	
	public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
		this.bankTransactions = bankTransactions;
	}
	
	public double calculateTotalAmount() {
		double total = 0;
		for(final BankTransaction bankTransaction: bankTransactions) {
			total += bankTransaction.getAmount();
		}
		
		return total;
	}
	
	public double calculateTotalInMonth(final Month month) {
		double total = 0;
		for(final BankTransaction bankTransaction: bankTransactions) {
			if(bankTransaction.getDate().getMonth() == month) {
				total += bankTransaction.getAmount();
			}
		}
		
		return total;
	}
	
	public double calculateTotalForCategory(final String category) {
		double total = 0;
		for(final BankTransaction bankTransaction: bankTransactions) {
			if(bankTransaction.getDescription().equals(category)) {
				total += bankTransaction.getAmount();
			}
		}
		
		return total;
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
}
