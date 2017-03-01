package com.github.sailarize.asserts;

import org.junit.Assert;

import com.github.sailarize.test.utils.JsonReder;

/**
 * Asserts about serializations.
 * 
 * @author agusmunioz
 *
 */
public class AssertSerialization {

    /**
     * Asserts an object is correctly serialized.
     * 
     * @param message
     *            the message for assertion failure.
     * @param file
     *            the file holding the expected json
     * @param object
     *            the resource serialized to serialize.
     */
    public static void assertEquals(String message, String file, String resource) {

        String expected = JsonReder.json(file);

        Assert.assertEquals(message, expected, resource);
    }
}
