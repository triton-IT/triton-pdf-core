package com.web4enterprise.pdf.core.exceptions;

/**
 * Exception thrown when PDF cannot be generated correctly.
 * 
 * @author Régis Ramillien
 */
public class PdfGenerationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PdfGenerationException(String message, Throwable cause) {
		super(message, cause);
	}
}
