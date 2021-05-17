package com.iteratrlearning.shu_book.chapter_03;

import com.iteratrlearning.shu_book.chapter_02.BankTransaction;

@FunctionalInterface
public interface BankTransactionFilter {
	boolean test(BankTransaction bankTransaction);
}
