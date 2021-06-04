package com.iteratrlearning.shu_book.chapter_04;

import java.io.IOException;

public class UnknownFileTypeException extends IOException {
	public UnknownFileTypeException() {
		super();
	}
	
	public UnknownFileTypeException(String exp) {
		super(exp);
	}
}
