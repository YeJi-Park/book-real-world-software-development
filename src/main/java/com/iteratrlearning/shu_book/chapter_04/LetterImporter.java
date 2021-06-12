package com.iteratrlearning.shu_book.chapter_04;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.iteratrlearning.shu_book.chapter_04.Attributes.*;


public class LetterImporter implements Importer {

	@Override
	public Document importFile(File file) throws IOException {
		final TextFile textFile = new TextFile(file);
		
		textFile.addLineSuffix(NAME_PREFIX, PATIENT);
		
		final int lineNumber = textFile.addLines(2, String::isEmpty, ADDRESS);
		textFile.addLines(lineNumber + 1, (line) -> line.startsWith("regards,"), BODY);
		
		final Map<String, String> attributes = new HashMap<>();
		attributes.put(TYPE, "LETTER");
		
		return new Document(attributes);
	}

}
