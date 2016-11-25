package com.web4enterprise.pdf.core.exceptions;

/**
 * Runtime exception thrown when simplypdf-core is not configured correctly.
 * 
 * @author Régis Ramillien
 */
public class ConfigurationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
