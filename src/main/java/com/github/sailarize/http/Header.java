package com.github.sailarize.http;

import com.github.sailarize.utils.ToStringBuilder;

/**
 * Models an HTTP header.
 * 
 * @author agusmunioz
 *
 */
public class Header {

    private String name;

    private String value;

    /**
     * Creates an initialized {@link Header}.
     * 
     * @param name
     *            the header name.
     * 
     * @param value
     *            the header value.
     */
    public Header(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Gets the header name.
     * 
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the header name.
     * 
     * @param name
     *            the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the header value.
     * 
     * @return the header value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the header value.
     * 
     * @param value
     *            the value.
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {

        return ToStringBuilder.toString(this);
    }
}
