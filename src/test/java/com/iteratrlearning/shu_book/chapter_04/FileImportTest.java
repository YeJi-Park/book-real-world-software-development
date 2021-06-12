package com.iteratrlearning.shu_book.chapter_04;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.util.List;

import static com.iteratrlearning.shu_book.chapter_04.Attributes.*;

import org.junit.jupiter.api.Test;

public class FileImportTest {
	
	private DocumentManagementSystem system = new DocumentManagementSystem();
	
	private final static String RESOURCES = "src/test/resources/";
	
	final static String INVOICE = RESOURCES+"patient.letter";
	final static String LETTER = RESOURCES+"patient.letter";
//	final static String REPORT = RESOURCES+"patient.letter";
	final static String IMAGE = RESOURCES+"xray.jpg";
	
	
	@Test
	public void shouldImportFile() throws Exception{
		system.importFile(LETTER);
		
		final Document document = onlyDocument();
		assertAttributeEquals(document, Attributes.PATH, LETTER);
	}
	
	@Test
	public void shouldImportImageAttributes() throws Exception{
		system.importFile(IMAGE);
		
		final Document document = onlyDocument();
		
		assertAttributeEquals(document, WIDTH, "320");
		assertAttributeEquals(document, WIDTH, "179");
		assertAttributeEquals(document, TYPE, "IMAGE");
	}
	
	@Test
	public void shouldNotImportMissingFile() throws Exception{
		assertThrows(FileNotFoundException.class, 
				()-> system.importFile("gobbledygook.txt"));
	}
	
	@Test
	public void shouldNotImportUnknownFile() throws Exception
	{
		assertThrows(UnknownFileTypeException.class, 
				()-> system.importFile(RESOURCES+"unknown.txt"));
	}
	
	
	private void assertAttributeEquals(
			final Document document,
			final String attributeName,
			final String expected)
	{
		assertEquals("Document has the wrong value for "+attributeName, 
				expected,
				document.getAttribute(attributeName));
	}
	
	private Document onlyDocument() {
		final List<Document> documents = system.contents();
		assertThat(documents, hasSize(1));
		
		return documents.get(0);
	}

}
