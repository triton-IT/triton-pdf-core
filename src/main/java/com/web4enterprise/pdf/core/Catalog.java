package com.web4enterprise.pdf.core;

import java.io.IOException;
import java.io.OutputStream;

public class Catalog implements PDFObject {
	@Override
	public int write(OutputStream stream) throws PdfGenerationException {
		String asString = "1 0 obj" + LINE_SEPARATOR
		 		+ "<< /Type /Catalog" + LINE_SEPARATOR
				+ "    /Pages 2 0 R" + LINE_SEPARATOR
				+ ">>" + LINE_SEPARATOR
				+ "endobj" + LINE_SEPARATOR;
		
		try {
			stream.write(asString.getBytes());
		} catch (IOException e) {
			throw new PdfGenerationException("Cannot write to output stream", e);
		}
		
		return asString.length();
	}
	
	@Override
	public int getId() {
		return 1;
	}
}
