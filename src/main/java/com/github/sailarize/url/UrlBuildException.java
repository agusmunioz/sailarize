package com.github.sailarize.url;

import com.github.sailarize.resource.Path;

/**
 * Exception thrown when a resource is not annotated with {@link Path}
 * 
 * @author agustin.munoz
 * 
 */
public class UrlBuildException extends RuntimeException {

	private static final long serialVersionUID = -2611700349949791568L;

	/**
	 * Creates an initialized {@link UrlBuildException}.
	 * 
	 * @param message
	 *            the error message.
	 */
	public UrlBuildException(String message) {

		super(message);
	}

}
