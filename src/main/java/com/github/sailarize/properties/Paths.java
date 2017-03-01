package com.github.sailarize.properties;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Obtains paths from sail/paths.properties file.
 * 
 * @author gonzalogtesta
 *
 */
public class Paths {

    private static final String BASE_NAME = "sail/paths";

    /**
     * Gets a path.
     * 
     * @param key
     *            the path key in the properties file.
     * 
     * @param args
     *            for variable values in the path value.
     * 
     * @return the path or null if the key or the properties file is not found.
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
