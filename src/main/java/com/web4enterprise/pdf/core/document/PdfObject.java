package com.web4enterprise.pdf.core.document;

import java.io.OutputStream;

import com.web4enterprise.pdf.core.exceptions.PdfGenerationException;

public interface PdfObject {	
	/**
	 * Render an object to output stream.
	 * 
	 * @param stream The output stream where object will be rendered.
	 * @return The number of bytes rendered.
	 * @throws PdfGenerationException When object cannot be rendered to output stream.
	 */
	int write(OutputStream stream) throws PdfGenerationException;
	
	/**
	 * Get the identifier of this object.
	 * 
	 * @return The identifier of this object in document.
	 */
	int getId();
}
