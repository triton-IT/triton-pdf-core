package com.web4enterprise.pdf.core.exceptions;

/**
 * Runtime exception thrown when simplypdf-core is not configured correctly.
 * 
 * @author Régis Ramillien
 */
public class ConfigurationException extends RuntimeException {
	/**
	 * The default serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
	public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
