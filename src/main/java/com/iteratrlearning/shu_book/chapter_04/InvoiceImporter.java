package com.iteratrlearning.shu_book.chapter_04;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.iteratrlearning.shu_book.chapter_04.Attributes.*;

public class InvoiceImporter implements Importer {

	@Override
	public Document importFile(final File file) throws IOException {
		final TextFile textFile = new  TextFile(file);
		
		textFile.addLineSuffix(NAME_PREFIX, PATIENT);
		textFile.addLineSuffix(AMOUNT_PREFIX, AMOUNT);
		
		final Map<String, String> attributes = new HashMap<>();
		attributes.put(TYPE, "INVOICE");
		
		return new Document(attributes);
	}

}
