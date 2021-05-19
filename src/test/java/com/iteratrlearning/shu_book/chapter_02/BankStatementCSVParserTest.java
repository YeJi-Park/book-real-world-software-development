package com.iteratrlearning.shu_book.chapter_02;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankStatementCSVParserTest {
	
	private final BankStatementParser statementParser =
			new BankStatementCSVParser();
	
	@Test
	public void shouldParseOneCorrectLine() throws Exception{
		final String line = "30-01-2017,-50,Tesco";
		final BankTransaction result = statementParser.parseFrom(line);
		
		final BankTransaction expected =
				new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
		
		final double tolerance = 0.0d;
		
		Assertions.assertEquals(expected.getDate(), result.getDate());
		Assertions.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
		Assertions.assertEquals(expected.getDescription(), result.getDescription());
	}
	
	@Test
	public void shouldFailToParseUnvalidDateFormatData() throws Exception{
		final String line = "30-13-2017,-50,Tesco";
		Assertions.assertThrows(DateTimeParseException.class, () ->{			
			statementParser.parseFrom(line);
		});
	}
	
	@Test
	public void shoudParseSeveralCorrectLines() throws Exception{
		final String[] lines = {
				"30-12-2017,-50,Tesco",
				"01-02-2021,200,A",
				"05-01-2021,500,B"
		};
		
		final List<BankTransaction> result = 
				statementParser.parseLinesFrom(Arrays.asList(lines));
		
		final BankTransaction[] expected =
				{
						new BankTransaction(LocalDate.of(2017, Month.DECEMBER, 30), -50, "Tesco"),
						new BankTransaction(LocalDate.of(2021, Month.FEBRUARY, 1), 200, "A"),
						new BankTransaction(LocalDate.of(2021, Month.JANUARY, 5), 500, "B")
				};
		
		final double tolerance = 0.0d;
		
		for(int i=0; i<lines.length; i++) {
			Assertions.assertEquals(expected[i].getDate(), result.get(i).getDate());
			Assertions.assertEquals(expected[i].getAmount(), result.get(i).getAmount(), tolerance);
			Assertions.assertEquals(expected[i].getDescription(), result.get(i).getDescription());
		}
	}
}
