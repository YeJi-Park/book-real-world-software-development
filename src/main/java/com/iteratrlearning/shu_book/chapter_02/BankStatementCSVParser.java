package com.iteratrlearning.shu_book.chapter_02;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {
	private static final DateTimeFormatter DATE_PATTERN
		= DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public BankTransaction parseFrom(final String line) {
		final String[] cols = line.split(",");
		
		final LocalDate date = LocalDate.parse(cols[0], DATE_PATTERN);
		final double amount = Double.parseDouble(cols[1]);
		final String description = cols[2];
		
		return new BankTransaction(date, amount, description);
	} 

	public List<BankTransaction>parseLinesFrom(final List<String> lines) {
		final List<BankTransaction> bankTransactions = new ArrayList<>();
		
		for(final String line: lines) {
			bankTransactions.add(parseFrom(line));
		}
		
		return bankTransactions;
	}
}
