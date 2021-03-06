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

    protected static final String QUERY = "?";

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

    /**
     * Appends a query string to this query. That means, it incorporates all the
     * parameters.
     * 
     * @param query
     *            the query to append.
     */
    public void append(QueryString query) {

        if (this.query == null) {
            this.query = new StringBuilder(query.toString());
        } else {
            this.query.append(query.toString().replaceFirst("\\" + QUERY, AND));
        }
    }

    @Override
    public String toString() {

        return (this.query != null) ? query.toString() : "";
    }

    /**
     * Determines if the specified parameter is included in this query string.
     * 
     * @param name
     *            the parameter name.
     * 
     * @return true if it is included, false otherwise.
     */
    public boolean contains(String name) {

        return this.query != null && this.query.indexOf(name) > 0;
    }

}
