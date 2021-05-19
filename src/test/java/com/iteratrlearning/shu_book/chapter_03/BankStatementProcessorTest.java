package com.iteratrlearning.shu_book.chapter_03;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.iteratrlearning.shu_book.chapter_02.BankStatementProcessor;
import com.iteratrlearning.shu_book.chapter_02.BankTransaction;

public class BankStatementProcessorTest {
	
	@Test
	public void shouldFilterTransactionInFeb() {
		final BankTransaction febrarySalary = 
				new BankTransaction(LocalDate.of(2019, Month.FEBRUARY, 01), 2_000, "salary");
		
		final BankTransaction marchSalary = 
				new BankTransaction(LocalDate.of(2019, Month.MARCH, 01), 2_000, "salary");
		
		final List<BankTransaction> bankTransactions = 
				List.of(febrarySalary, marchSalary);
		
		final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
		final List<BankTransaction> transactions = 
				bankStatementProcessor.findTranactions(bankTransaction ->
					bankTransaction.getDate().getMonth() == Month.FEBRUARY &&
					bankTransaction.getAmount() >= 1_000 
				);
		
		assertThat(transactions, contains(febrarySalary));
		assertThat(transactions, hasSize(1));
	}
	

}
