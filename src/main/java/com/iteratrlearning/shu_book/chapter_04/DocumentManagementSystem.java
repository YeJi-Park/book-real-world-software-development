package com.iteratrlearning.shu_book.chapter_04;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentManagementSystem {
	
	private final Map<String, Importer> extensionToImporter = new HashMap<>();
	
	public DocumentManagementSystem() {
//		extensionToImporter.put("letter", new LetterImporter());
//		extensionToImporter.put("report", new ReportImporter());
		extensionToImporter.put("jpg", new ImageImporter());
		
	}
	
	void importFile(String file) {
	
	}
	
	List<Document> contents(){
		
	}
}
