package com.iteratrlearning.shu_book.chapter_04;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentManagementSystem {
	
	private final Map<String, Importer> extensionToImporter = new HashMap<>();
	private List<Document> documents = new ArrayList<>();
	
	public DocumentManagementSystem() {
//		extensionToImporter.put("letter", new LetterImporter());
//		extensionToImporter.put("report", new ReportImporter());
		extensionToImporter.put("jpg", new ImageImporter());
		
	}
	
	public void importFile(String path) throws IOException {
		final File file = new File(path);
		
		if(!file.exists()) {
			throw new FileNotFoundException(path);
		}
		
		final int seperatorIndex = path.lastIndexOf('.');
		
		if(seperatorIndex != -1) {
			if(seperatorIndex == path.length()) {
				throw new UnknownFileTypeException("No extension found for file: "+path);
			}
			
			final String extension = path.substring(seperatorIndex+1);
			final Importer importer = extensionToImporter.get(extension);
			
			if(importer == null) {
				throw new UnknownFileTypeException("For file: "+ path);
			}
			
			final Document document = importer.importFile(file);
			documents.add(document);
		}else {
			throw new UnknownFileTypeException("No extension found for file:"+path);
		}
		
	}
	
	List<Document> contents(){
		return List.copyOf(documents);
	}
}
