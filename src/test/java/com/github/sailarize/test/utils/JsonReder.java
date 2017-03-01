package com.github.sailarize.test.utils;

import java.io.InputStream;

import org.junit.Assert;

/**
 * Utilities for testing Json purposes.
 * 
 * @author agusmunioz
 *
 */
public class JsonReder {

    private static final String JSON_FOLDER = "/com/github/sailarize/examples/";

    /**
     * Reads a Json file from classpath and removes any space or new.
     * 
     * @param file
     *            the classpath file name.
     *
     * @return the Json as a string or null if the file was not found.
     */
    public static String json(String file) {

        try {

            InputStream is = JsonReder.class.getResourceAsStream(JSON_FOLDER + file + ".json");

            int ch;

            StringBuilder sb = new StringBuilder();

            while ((ch = is.read()) != -1) {

                if (ch != ' ' && ch != '\n') {
                    sb.append((char) ch);
                }

            }

            return sb.toString();

        } catch (Exception e) {
            Assert.fail(
                    "An exception occurred while reading a Json file from classpath. File: " + file + ". Error: " + e);
            return null;
        }

    }

}
