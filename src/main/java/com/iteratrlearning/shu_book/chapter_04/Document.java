package com.iteratrlearning.shu_book.chapter_04;

import java.util.Map;

public class Document {
	private final Map<String, String> attributes;
	
	// default 접근자 사용 -> 문서 관리 시스템에서만 접근 가능하도록
	Document(final Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	public String getAttribute(final String attrbuteName) {
		return attributes.get(attrbuteName);
	}
}
