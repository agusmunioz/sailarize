package com.github.sailarize.properties;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Obtains hosts from sail/hosts.properties file.
 * 
 * @author agusmunioz
 *
 */
public class Hosts {

	private static final String BASE_NAME = "sail/hosts";

	/**
	 * Gets a host name.
	 * 
	 * @param key
	 *            the host key in the properties file.
	 * 
	 * @return the host or null if the key or the properties file is not found.
	 */
	public static String get(String key) {

		try {

			return ResourceBundle.getBundle(BASE_NAME).getString(key);

		} catch (MissingResourceException e) {
			return null;
		}
	}
}
