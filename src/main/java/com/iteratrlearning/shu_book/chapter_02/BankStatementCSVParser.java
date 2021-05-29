package com.iteratrlearning.shu_book.chapter_02;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.iteratrlearning.shu_book.chapter_03.BankStatementValidator;
import com.iteratrlearning.shu_book.chapter_03.CSVSyntaxException;

public class BankStatementCSVParser implements BankStatementParser {
	
	private final int EXPECTED_ATTRIBUTES_LENGTH = 3;
	
	private static final DateTimeFormatter DATE_PATTERN
		= DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public BankTransaction parseFrom(final String line) {
		final String[] cols = line.split(",");
		
		if(cols.length < EXPECTED_ATTRIBUTES_LENGTH) {
			throw new CSVSyntaxException();
		}
		
		// BankStatementValidator validator = new BankStatementValidator(cols[0], cols[1], cols[2]);
		// if( validator.validate().hasErrors() ) ?
		
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
