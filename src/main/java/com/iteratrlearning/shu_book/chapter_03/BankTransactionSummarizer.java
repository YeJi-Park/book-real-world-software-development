package com.iteratrlearning.shu_book.chapter_03;

import com.iteratrlearning.shu_book.chapter_02.BankTransaction;

public interface BankTransactionSummarizer {
	double summarize(double accumulator, BankTransaction bankTransaction);
}
