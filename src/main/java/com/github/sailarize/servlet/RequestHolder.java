package com.github.sailarize.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 * Holds the current {@link HttpServletRequest} in a {@link ThreadLocal}.
 * 
 * @author agusmunioz
 * 
 */
public class RequestHolder {

	private static ThreadLocal<HttpServletRequest> REQUEST = new ThreadLocal<HttpServletRequest>();

	/**
	 * Gets the current {@link HttpServletRequest}.
	 * 
	 * @return the request.
	 */
	public static HttpServletRequest get() {

		return REQUEST.get();
	}

	/**
	 * Sets the current {@link HttpServletRequest}.
	 * 
	 * @param request
	 *            the current request.
	 */
	public static void set(HttpServletRequest request) {
		REQUEST.set(request);
	}

	/**
	 * Cleans the {@link ThreadLocal}.
	 */
	public static void clean() {
		REQUEST.remove();
	}

}
