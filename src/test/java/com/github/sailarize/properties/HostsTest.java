package com.github.sailarize.properties;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for {@link Hosts}.
 * 
 * @author agusmunioz
 *
 */
public class HostsTest {

	/**
	 * Test the return of a host with an existing key.
	 */
	@Test
	public void existing() {

		String host = Hosts.get("host.test");

		Assert.assertEquals("Unexpected host from properties file.", "cdn.test.com", host);
	}

	/**
	 * Test a null is returned when a not existing key is used.
	 */
	@Test
	public void notExisting() {

		String host = Hosts.get("not.exist.key");

		Assert.assertNull("A host ir returned with an unexistent key.", host);
	}
}
