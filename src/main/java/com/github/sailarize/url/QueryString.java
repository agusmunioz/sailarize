package com.github.sailarize.url;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Models a URL query string.
 * 
 * @author agusmunioz
 * 
 */
public class QueryString {

	private static final String QUERY = "?";

	private static final String AND = "&";

	private static final String EQUALS = "=";

	private StringBuilder query;

	/**
	 * Adds a parameter to the query string.
	 * 
	 * @param name
	 *            the parameter name.
	 * 
	 * @param value
	 *            the parameter value.
	 */
	public void add(String name, String value) {

		if (this.query == null) {

			this.query = new StringBuilder(QUERY);

		} else {

			this.query.append(AND);

		}

		this.query.append(name).append(EQUALS).append(this.encode(value));

	}

	/**
	 * URL encodes a parameter value.
	 * 
	 * @param value
	 *            the value to be encoded.
	 * 
	 * @return the encoded value or the same value if the character encoding.
	 */
	private String encode(String value) {

		try {

			return URLEncoder.encode(value, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			return value;
		}

	}

	@Override
	public String toString() {

		return this.query.toString();
	}

}
