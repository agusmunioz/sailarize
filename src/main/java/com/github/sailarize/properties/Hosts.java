package com.github.sailarize.properties;

import java.text.MessageFormat;
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
	 * @param args
	 *            for variable values in the host value.
	 * 
	 * @return the host or null if the key or the properties file is not found.
	 */
	public static String get(String key, String... args) {

		try {

			String value = ResourceBundle.getBundle(BASE_NAME).getString(key);

			if (args == null || args.length == 0) {
				return value;
			}

			return MessageFormat.format(value, (Object[]) args);

		} catch (MissingResourceException e) {
			return null;
		}
	}
}
