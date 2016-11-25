package com.web4enterprise.pdf.core.document;

import static com.web4enterprise.pdf.core.document.Pdf.LINE_SEPARATOR;

import java.io.IOException;
import java.io.OutputStream;

import com.web4enterprise.pdf.core.exceptions.PdfGenerationException;

public class Catalog implements PdfObject {
	@Override
	public int write(OutputStream stream) throws PdfGenerationException {
		String asString = "1 0 obj <<" + LINE_SEPARATOR
		 		+ "/Type /Catalog" + LINE_SEPARATOR
				+ "/Pages 2 0 R" + LINE_SEPARATOR
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
