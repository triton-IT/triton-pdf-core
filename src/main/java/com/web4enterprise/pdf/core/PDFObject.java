package com.web4enterprise.pdf.core;

public interface PDFObject {
	static String LINE_SEPARATOR = "\n";
	
	/**
	 * The rendering as PDF.
	 * @return The PDF syntax of this object.
	 * @throws PdfGenerationException
	 */
	String asString() throws PdfGenerationException;
	
	int getId();
}
