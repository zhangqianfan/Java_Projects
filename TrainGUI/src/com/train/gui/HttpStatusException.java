package com.train.gui;

import java.io.IOException;

/**
 * Thrown to indicate that the HTTP status is exceptional (greater than 300).
 * @author  ÕÅ´¨¿¸°Ñ×Ó
 */
public class HttpStatusException extends IOException {

	private static final long serialVersionUID = -462824170384842125L;
	
	/**
     * Constructs a new <code>HttpStatusException</code> with no detail message.
     */
	public HttpStatusException() {
	}

	/**
     * Constructs a new <code>HttpStatusException</code> with the
     * specified detail message.
     *
     * @param   status   the detail message.
     */
	public HttpStatusException(String status) {
		super(status);
	}
	
}
