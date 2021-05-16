package com.iteratrlearning.shu_book.chapter_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;


public class BankTransactionAnalyzer {
	private static final String RESOURCES = "src/main/resources/";
	
	public void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException {
		final Path path = Paths.get(RESOURCES + fileName);
		final List<String> lines = Files.readAllLines(path); 

		final List<BankTransaction> bankTransactions 
			= bankStatementParser.parseLinesFrom(lines);
		final BankStatementProcessor bankStatementProcessor
			= new BankStatementProcessor(bankTransactions);
		
		collectSummary(bankStatementProcessor);
	}

	
	private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
		System.out.println("Total Amount:"+bankStatementProcessor.calculateTotalAmount());
		
		System.out.println("Transactions of January:"+bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
		System.out.println("Transactions of February:"+bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));

		System.out.println("Total Salary:"+bankStatementProcessor.calculateTotalForCategory("Salary"));
		
		System.out.println("Max Amount for all periods:"+
				bankStatementProcessor.getMaxAmountInDateRange(LocalDate.of(1990, Month.JANUARY, 1), LocalDate.of(2021, Month.DECEMBER, 31)));
		
		System.out.println("Min Amount for all periods:"+
				bankStatementProcessor.getMinAmountInDateRange(LocalDate.of(1990, Month.JANUARY, 1), LocalDate.of(2021, Month.DECEMBER, 31)));
	}
}
