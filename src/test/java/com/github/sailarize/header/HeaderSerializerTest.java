package com.github.sailarize.header;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.github.sailarize.asserts.AssertSerialization;

/**
 * Test that any serialization implementation must run (extend) in order to
 * ensure a valid serialization of a headers.
 * 
 * @author agusmunioz
 *
 */
public abstract class HeaderSerializerTest {

	/**
	 * Test the serialization of a map with header values.
	 */
	@Test
	public void serialization() {

		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("header1", "aValue1");
		headers.put("header2", "aValue2");

		String resource = this.serialize(headers);

		AssertSerialization.assertEquals("Unexpected headers serialization.", "headers", resource);

	}

	/**
	 * Test the serialization of an empty {@link Map} of headers.
	 */
	@Test
	public void emptyHeaders() {

		Map<String, String> headers = new LinkedHashMap<String, String>();

		String resource = this.serialize(headers);

		AssertSerialization.assertEquals("Unexpected empty headers serialization.", "headers_empty", resource);
	}

	/**
	 * Test the serialization of a null {@link Map} of headers.
	 */
	@Test
	public void nullHeaders() {

		String resource = this.serialize(null);

		AssertSerialization.assertEquals("Unexpected empty headers serialization.", "headers_empty", resource);

	}

	/**
	 * Serializes a map of headers.
	 * 
	 * @param headers
	 *            the headers.
	 * 
	 * @return the generated Json.
	 */
	protected abstract String serialize(Map<String, String> headers);
}
