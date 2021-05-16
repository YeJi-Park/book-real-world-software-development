package com.iteratrlearning.shu_book.chapter_02;

import java.io.IOException;

public class MainApplication {

	public static void main(String[] args) throws IOException {
		final BankTransactionAnalyzer bankStatementAnalyzer
		 	= new BankTransactionAnalyzer();
		 final BankStatementParser bankStatementParser
		 	= new BankStatementCSVParser();
		 bankStatementAnalyzer.analyze(args[0], bankStatementParser);
	}

}
