package com.iteratrlearning.shu_book.chapter_04;

import java.io.File;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import static com.iteratrlearning.shu_book.chapter_04.Attributes.*;

public class ImageImporter implements Importer {

	@Override
	public Document importFile(File file) throws IOException {
		final Map<String, String> attributes = new HashMap<>();
		attributes.put(PATH, file.getPath());
		
		final BufferdImage image = ImageIO.read(file);
		attributes.put(WIDTH, String.valueOf(image.getWidth()));
		attributes.put(HEIGHT, String.valueOf(image.getHeight()));
		attributes.put(TYPE, "IMAGE");
		
		return new Document(attributes);
	}

}
