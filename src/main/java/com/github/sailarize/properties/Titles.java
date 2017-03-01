package com.github.sailarize.properties;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Obtains links and form titles from localized properties files. The properties
 * files must be named <b>titles{_language_COUNTRY}.properties</b> an be located
 * under the folder <b>classpath:sail/</b>
 * 
 * <ul>
 * <li>titles.properties (default for any language and country)</li>
 * <li>titles_en.properties (default for English language)</li>
 * <li>titles_en_US.properties (titles for US English)</li>
 * </ul>
 * 
 * @author agusmunioz
 *
 */
public class Titles {

    private static final String BASE_NAME = "sail/titles";

    private static String ENCODING;

    /**
     * Configures the expected encoding for titles.
     * 
     * @param encoding
     */
    public static void encoding(String encoding) {
        ENCODING = encoding;
    }

    /**
     * Gets an I18N title.
     * 
     * @param key
     *            the title key in the properties file.
     * 
     * @param locale
     *            for localization.
     * 
     * @return the title or null if the key or the properties file is not found.
     */
    public static String get(String key, Locale locale) {

        try {

            ResourceBundle bundle = ResourceBundle.getBundle(BASE_NAME, locale, new ResourceBundle.Control() {
                @Override
                public Locale getFallbackLocale(String baseName, Locale locale) {
                    return Locale.ROOT;
                }
            });

            String title = bundle.getString(key);

            if (ENCODING == null || ENCODING.isEmpty()) {

                return title;
            }

            return new String(title.getBytes(), ENCODING);

        } catch (MissingResourceException e) {
            return null;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * Gets the I18N title from the default properties file
     * (classpath:/sail/titles.properties).
     * 
     * @param key
     *            the title key in the properties file.
     * 
     * @return the title or null if the key or the properties file is not found.
     */
    public static String get(String key) {

        try {

            return ResourceBundle.getBundle(BASE_NAME, Locale.ROOT).getString(key);

        } catch (MissingResourceException e) {
            return null;
        }
    }
}
